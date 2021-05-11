package org.topnetwork.analysis.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.topnetwork.analysis.block.NodeInfoLoader;
import org.topnetwork.analysis.block.NodeIssuanceLoader;

/**
 * @author CasonCai
 * @since 2021/4/29 6:41 下午
 **/
@Slf4j
@Component
public class SyncNodeInfoJob{

    @Autowired
    NodeInfoLoader nodeInfoLoader;

    @Autowired
    NodeIssuanceLoader nodeIssuanceLoader;

    /**
     * 同步节点信息
     * @param s
     * @return
     * @throws Exception
     */
    @XxlJob("syncNodeInfoJob")
    public ReturnT<String> syncNodeInfoJob(String s) throws Exception {
        log.info("start SyncNodeInfoJob");

        nodeInfoLoader.loadAllNodeInfo();

        return ReturnT.SUCCESS;
    }

    @XxlJob("syncNodeElectionJob")
    public ReturnT<String> syncNodeElectionJob(String s)throws Exception{
        log.info("start syncNodeInfoJob");

        Long startHeight = null;
        try{
            if(StringUtils.hasLength(s)) {
                startHeight = Long.parseLong(s);
            }
        }catch (Exception e){
            log.error("param error",e);
            return ReturnT.FAIL;
        }
        nodeInfoLoader.loadElectInfo(startHeight);
        return ReturnT.SUCCESS;
    }

    /**
     * 同步每期的节点奖励信息
     * @param s
     * @return
     * @throws Exception
     */
    @XxlJob("syncNodeIssuanceJob")
    public ReturnT<String> syncNodeIssuanceJob(String s) throws Exception {
        log.info("start SyncNodeIssuanceJob");

        nodeIssuanceLoader.loadNextIssuance();
        return ReturnT.SUCCESS;
    }
}
