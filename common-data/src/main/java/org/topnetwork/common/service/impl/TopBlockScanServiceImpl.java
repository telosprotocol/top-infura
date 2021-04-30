package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.topnetwork.common.entity.TopBlockScan;
import org.topnetwork.common.dao.TopBlockScanDao;
import org.topnetwork.common.enums.BlockType;
import org.topnetwork.common.service.TopBlockScanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-26
 */
@Service
public class TopBlockScanServiceImpl extends ServiceImpl<TopBlockScanDao, TopBlockScan> implements TopBlockScanService {

    @Override
    public void plusScanHeight(String address, Long count) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);

        TopBlockScan topBlockScan = getOne(queryWrapper);
        if(topBlockScan == null){
            topBlockScan = new TopBlockScan();
            topBlockScan.setAddress(address);
            topBlockScan.setBlockType(BlockType.TableBlock);
            save(topBlockScan);
        }

        baseMapper.plusScanHeight(address, count);
    }

    @Override
    public Long getScanHeight(String address) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);

        TopBlockScan topBlockScan = getOne(queryWrapper);

        if(topBlockScan != null){
            return topBlockScan.getScanHeight();
        }else{
            return 0L;
        }
    }
}
