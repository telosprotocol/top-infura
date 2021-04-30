package org.topnetwork.analysis.block;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.topnetwork.grpclib.pojo.stream.TableBlockResult;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.util.Iterator;

/**
 * 订阅最新的tableblock区块
 * @author CasonCai
 * @since 2021/4/20 下午6:07
 **/
@Slf4j
@Component
public class TableBlockSubscriber implements ApplicationRunner {
    @Autowired
    TopGrpcClient grpcClient;

    @Autowired
    TableBlockLoader tableBlockLoader;

    private boolean running = false;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread thread = new Thread(){
            @Override
            public void run() {
                subscribe();
            }
        };

        thread.start();
    }

    /**
     * 扫描tableblock
     */
    public void subscribe(){
        running = true;
        while(running){
            doScanTableBlock();
        }
    }

    public void stop(){
        running = false;
    }

    private void doScanTableBlock(){
        try {
            Iterator<TableBlockResult> iterator = grpcClient.getTableBlockStream();
            while (iterator.hasNext() && running) {
                TableBlockResult tableBlockResult = iterator.next();
                log.info("receive tableblock, height={}", tableBlockResult.getValue().getHeight());
                tableBlockLoader.loadTableBlock(tableBlockResult);
            }
        }catch (Exception e){
            log.error("scan tableblock error",e);
        }

    }

}
