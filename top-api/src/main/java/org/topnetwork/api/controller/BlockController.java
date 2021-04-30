package org.topnetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.manager.TableBlockManager;
import org.topnetwork.api.manager.UnitBlockManager;
import org.topnetwork.common.entity.TopUnitBlock;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.math.BigInteger;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/30 10:03 上午
 **/
@RequestMapping("/block")
@RestController
public class BlockController {

    @Autowired
    TopGrpcClient topGrpcClient;

    @Autowired
    TableBlockManager tableBlockManager;

    @Autowired
    UnitBlockManager unitBlockManager;


    @GetMapping("/getTableBlock")
    public CommonResult getTableBlock(String address, Long height){

        return CommonResult.success();
    }

    @GetMapping("/getLatestTableBlockHeight")
    public CommonResult<Map<String, BigInteger>> getLatestTableBlockHeight(){
        Map<String, BigInteger> latestTabelBlockMap = tableBlockManager.getLatestTableBlockHeight();
        return CommonResult.success(latestTabelBlockMap);
    }



    @GetMapping("/getUnitBlocks")
    public CommonResult<PageData<TopUnitBlock>> getUnitBlocks(@RequestParam(name = "address", required = false) String address,
                                                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        PageData<TopUnitBlock> unitBlockPageData = unitBlockManager.getTopUnitBlock(address, pageNum, pageSize);
        return CommonResult.success(unitBlockPageData);
    }





}
