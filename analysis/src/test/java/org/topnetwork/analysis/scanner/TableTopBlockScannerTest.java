package org.topnetwork.analysis.scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.topnetwork.analysis.AnalysisApplication;
import org.topnetwork.analysis.block.MultiAddressTableBlockScanner;
import org.topnetwork.analysis.block.TableBlockSubscriber;

/**
 * @author CasonCai
 * @since 2021/4/22 下午1:38
 **/
@SpringBootTest(classes = AnalysisApplication.class)
public class TableTopBlockScannerTest {

    @Autowired
    TableBlockSubscriber tableBlockSubscriber;

    @Autowired
    MultiAddressTableBlockScanner tableBlockScanner;

    @Test
    public void testScanner(){
        tableBlockScanner.startScan();
    }



}
