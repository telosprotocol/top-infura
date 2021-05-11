package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.topnetwork.common.constant.TopAddress;
import org.topnetwork.common.service.TopBlockScanService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author CasonCai
 * @since 2021/4/26 上午10:15
 **/
@Slf4j
@Component
public class MultiAddressTableBlockScanner implements InitializingBean {

    private ThreadPoolExecutor executor = null;

    private BlockingQueue<Runnable> blockingQueue = null;

    @Autowired
    TableBlockLoader tableBlockLoader;

    @Autowired
    TopBlockScanService topBlockScanService;

    @Value("${tableblock.scanner.thread.count:20}")
    private int tableBlockScannerThreadCount;

    @Override
    public void afterPropertiesSet() throws Exception {
        initExecutor();
    }

    private void initExecutor() {
        blockingQueue = new ArrayBlockingQueue<Runnable>(300);
        executor = new ThreadPoolExecutor(tableBlockScannerThreadCount, tableBlockScannerThreadCount,
                60, TimeUnit.SECONDS, blockingQueue,
                new CustomizableThreadFactory("tableblock-scanner-thread-"));
    }

    public void startScan() {
        ExecutorCompletionService completionService = new ExecutorCompletionService(executor);
        List<String> adresses = TopAddress.TABLEBLOCK_ADDRESSES;
        int totalTask = adresses.size();
        for (String address : adresses) {
            completionService.submit(new ScanBlockTask(address), null);
        }

        int completCount = 0;
        while(completCount < totalTask) {
            try {
                Future completFuture = completionService.take();
                completCount++;
            } catch (InterruptedException e) {
                log.error("take scan future error", e);
            }
        }
    }

    public Long addressCurrentHeight(String address) {
        return topBlockScanService.getScanHeight(address);
    }

    public boolean plusScanHeight(String address, Long count, boolean plusForce) {
        //大于10个再增加 减少数据库访问频率
        if((!plusForce && count >= 10) ||
                (plusForce && count > 0)){
            topBlockScanService.plusScanHeight(address, count);
            return true;
        }

        return false;
    }


    private class ScanBlockTask implements Runnable {
        private String address;

        public ScanBlockTask(String address) {
            this.address = address;
        }

        @Override
        public void run() {

            Long currentHeight = addressCurrentHeight(address);

            Long scanCount = 0L;

            while(true) {
                boolean success = tableBlockLoader.loadTableBlock(address, currentHeight + 1);
                if (success) {
                    currentHeight++;
                    scanCount++;

                    boolean plusSuccess = plusScanHeight(address, scanCount, true);
                    if(plusSuccess){
                        scanCount = 0L;
                    }
                } else {
                    break;
                }
            }
            plusScanHeight(address, scanCount, true);
            log.info("scan block finish, address={}", address);
        }
    }
}
