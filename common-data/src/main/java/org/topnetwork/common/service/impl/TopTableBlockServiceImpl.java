package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.topnetwork.common.constant.TopAddress;
import org.topnetwork.common.constant.TopConstants;
import org.topnetwork.common.entity.TopTableBlock;
import org.topnetwork.common.dao.TopTableBlockDao;
import org.topnetwork.common.service.TopTableBlockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.topnetwork.common.utils.TopUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * table块表 服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
@Service
public class TopTableBlockServiceImpl extends ServiceImpl<TopTableBlockDao, TopTableBlock> implements TopTableBlockService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void updateLatestHeight(String address, Long height) {
        Integer num = TopUtils.getTableBlockAddressNum(address);
        if (num == null) {
            return;
        }
        if (height == null) {
            return;
        }

        Object latestHeight = redisTemplate.opsForHash().get(TopConstants.TABLEBLCOK_LATEST_HEIGHT_KEY, address);

        if (latestHeight == null || height > (Long) latestHeight) {
            redisTemplate.opsForHash().put(TopConstants.TABLEBLCOK_LATEST_HEIGHT_KEY, address, height);
        }
    }

    @Override
    public Long getLatestHeight(String address) {
        Integer num = TopUtils.getTableBlockAddressNum(address);
        if (num == null) {
            return null;
        }

        Object latestHeight = redisTemplate.opsForHash().get(TopConstants.TABLEBLCOK_LATEST_HEIGHT_KEY, address);
        return (Long) latestHeight;
    }

    @Override
    public List<Long> getAllLatestHeight() {
        //从redis中读取最新高度，redis的数据可能并不全面
        Map<Object, Object> map = redisTemplate.opsForHash().entries(TopConstants.TABLEBLCOK_LATEST_HEIGHT_KEY);

        Long[] latestHeights = new Long[256];

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object address = entry.getKey();
            Object latestHeight = entry.getValue();
            Integer num = TopUtils.getTableBlockAddressNum(address.toString());
            if (num != null && num >= 0 && num < 256) {
                latestHeights[num] = (Long) latestHeight;
            }
        }

        //填充redis中未记录的最新高度
        for (int i = 0; i < latestHeights.length; i++) {
            Long height = latestHeights[i];
            if (height == null) {
                String address = TopAddress.BASE_TABLEBLOCK_ADDRESS + i;
                height = getLatestHeightFromDB(address);
                latestHeights[i] = height;
            }
        }
        return Arrays.asList(latestHeights);
    }

    private Long getLatestHeightFromDB(String address) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("max(height)");
        queryWrapper.eq("owner", address);


        List<Object> result = baseMapper.selectObjs(queryWrapper);

        Long latestHeight = 0L;
        if (result.size() > 0 && result.get(0) != null) {
            latestHeight = Long.parseLong(result.get(0).toString());
        }

        updateLatestHeight(address, latestHeight);

        return latestHeight;
    }
}
