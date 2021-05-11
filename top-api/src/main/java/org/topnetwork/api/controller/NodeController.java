package org.topnetwork.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.api.bean.CommonResult;
import org.topnetwork.api.bean.resp.NodeInfoResp;
import org.topnetwork.api.manager.NodeManager;
import org.topnetwork.common.entity.TopAccount;
import org.topnetwork.common.entity.TopNodeInfo;

/**
 * @author CasonCai
 * @since 2021/5/6 7:09 下午
 **/
@RequestMapping("/top/node")
@RestController
public class NodeController {

    @Autowired
    NodeManager nodeManager;

    @GetMapping("/queryNodeInfo")
    public CommonResult<NodeInfoResp> queryNodeInfo(String address){
        return CommonResult.success(nodeManager.getNode(address));
    }

    @GetMapping("/queryNodeReward")
    public CommonResult queryNodeReward(String address){


        return CommonResult.success();
    }

    @GetMapping("/listVoteUsed")
    public CommonResult listVoteUsed(String address){

        return CommonResult.success();
    }

    @GetMapping("/queryVoterDividend")
    public CommonResult queryVoterDividend(String address){
        return CommonResult.success();
    }

}
