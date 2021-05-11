package org.topnetwork.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/5/6 1:36 下午
 **/
@Api
@RequestMapping("/top/common")
@RestController
public class TopjController {

    @Autowired
    TopGrpcClient topGrpcClient;

    /**
     * 公共api，可调用所有主链grpc接口
     * 一般用来转发topj过来的请求
     * @param request
     * @return
     */
    @ApiOperation(value = "调用主链grpc接口",notes = "一般用来转发topj请求至主链grpc接口")
    @PostMapping("/callapi")
    public String topapi(HttpServletRequest request){

        Map<String, Object> params = new HashMap<>();

        Enumeration<String> paramterNames = request.getParameterNames();
        while(paramterNames.hasMoreElements()){
            String key = paramterNames.nextElement();
            String value = request.getParameter(key);
            params.put(key, value);
        }

        return topGrpcClient.callRequest(params);
    }

}
