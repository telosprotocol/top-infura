package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    private final static String TABLEBLOCK_ACCOUNT_BASE = "Ta0000gRD2qVpp2S7UpjAsznRiRhbE1qNnhMbEDp@";

    private List<String> addressList = new LinkedList();

    private ThreadPoolExecutor executor = null;

    private BlockingQueue<Runnable> blockingQueue = null;

    private boolean running = false;

    @Autowired
    TableBlockLoader tableBlockLoader;

    @Autowired
    TopBlockScanService topBlockScanService;

    @Override
    public void afterPropertiesSet() throws Exception {
        initExecutor();
        initAddress();
    }


    private void initExecutor() {
        blockingQueue = new ArrayBlockingQueue<Runnable>(256);
        executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, blockingQueue);
    }

    private void initAddress() {
        for (int i = 0; i < 256; i++) {
            String address = TABLEBLOCK_ACCOUNT_BASE + i;
            addressList.add(address);
        }
    }

    public void startScan() {
        ExecutorCompletionService completionService = new ExecutorCompletionService(executor);

        int totalTask = addressList.size();
        for (String address : addressList) {
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

    /**
     * 待扫描的用户列表
     *
     * @return
     */
    public List<String> scanAddressList() {
        return addressList;
    }

    public Long addressCurrentHeight(String address) {
        return topBlockScanService.getScanHeight(address);
    }

    public void plusScanHeight(String address, Long count) {
        topBlockScanService.plusScanHeight(address, count);
    }


    private class ScanBlockTask implements Runnable {
        private String address;
        /**
         * 每次执行的最大扫描高度，执行结束后将重新排队执行
         */
        private int scanCountPerOnce = 100;

        /**
         * 扫描完成
         */
        private boolean scanFinish = false;

        public ScanBlockTask(String address) {
            this.address = address;
        }

        @Override
        public void run() {
            Long currentHeight = addressCurrentHeight(address);

            Long scanCount = 0L;
            while(scanCount < scanCountPerOnce) {
                boolean success = tableBlockLoader.loadTableBlock(address, currentHeight + 1);
                log.info("scan tableblock address={}, height={},result={}", address, currentHeight+ 1,success);
                if (success) {
                    currentHeight++;
                    scanCount++;
                } else {
                    scanFinish = true;
                    break;
                }
            }
            log.info("scan block finish", address);

            plusScanHeight(address, scanCount);

        }
    }
}
