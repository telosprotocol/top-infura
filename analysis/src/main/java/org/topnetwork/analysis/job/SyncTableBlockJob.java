package org.topnetwork.analysis.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.topnetwork.analysis.block.MultiAddressTableBlockScanner;

/**
 * @author CasonCai
 * @since 2021/4/30 3:31 下午
 **/
@Slf4j
@Component
public class SyncTableBlockJob {

    @Autowired
    MultiAddressTableBlockScanner tableBlockScanner;

    @XxlJob("syncTableBlock")
    public ReturnT<String> syncTableBlock(String s) throws Exception {
        log.info("start SyncNodeIssuanceJob");
        tableBlockScanner.startScan();
        return ReturnT.SUCCESS;
    }


}
