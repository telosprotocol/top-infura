package org.topnetwork.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.topnetwork.common.entity.BlockScan;
import org.topnetwork.common.dao.BlockScanDao;
import org.topnetwork.common.enums.BlockType;
import org.topnetwork.common.service.BlockScanService;
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
public class BlockScanServiceImpl extends ServiceImpl<BlockScanDao, BlockScan> implements BlockScanService {

    @Override
    public void plusScanHeight(String address, Long count) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);

        BlockScan blockScan = getOne(queryWrapper);
        if(blockScan == null){
            blockScan = new BlockScan();
            blockScan.setAddress(address);
            blockScan.setBlockType(BlockType.TableBlock);
            save(blockScan);
        }

        baseMapper.plusScanHeight(address, count);
    }

    @Override
    public Long getScanHeight(String address) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("address", address);

        BlockScan blockScan = getOne(queryWrapper);

        if(blockScan!= null){
            return blockScan.getScanHeight();
        }else{
            return 0L;
        }
    }
}
