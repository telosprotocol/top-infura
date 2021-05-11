package org.topnetwork.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.PageData;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.bean.resp.UnitBlockResp;
import org.topnetwork.api.manager.TableBlockManager;
import org.topnetwork.api.manager.UnitBlockManager;
import org.topnetwork.common.service.TopTableBlockService;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.util.List;

/**
 * @author CasonCai
 * @since 2021/4/30 10:03 上午
 **/
@RequestMapping("/top/block")
@RestController
@Api
public class BlockController {

    @Autowired
    TopGrpcClient topGrpcClient;

    @Autowired
    TableBlockManager tableBlockManager;

    @Autowired
    UnitBlockManager unitBlockManager;


    @Autowired
    TopTableBlockService tableBlockService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/getTableBlock")
    public CommonResult getTableBlock(String address, Long height) throws JsonProcessingException {
        return CommonResult.success();
    }

    @GetMapping("/getLatestTableBlockHeight")
    public CommonResult<List<Long>> getLatestTableBlockHeight(){
        List<Long> latestTableBlockHeights = tableBlockService.getAllLatestHeight();
        return CommonResult.success(latestTableBlockHeights);
    }

    @GetMapping("/getUnitBlockList")
    public CommonResult<PageData<UnitBlockResp>> getUnitBlockList(@RequestParam(name = "address", required = false) String address,
                                                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){


        PageData<UnitBlockResp> unitBlockPageData = null;

        if(!ObjectUtils.isEmpty(address)){
            unitBlockPageData = unitBlockManager.getUnitBlocks(address, pageNum, pageSize);
        }else{
            unitBlockPageData = unitBlockManager.getUnitBlocks(pageNum, pageSize);
        }
        return CommonResult.success(unitBlockPageData);
    }
}
