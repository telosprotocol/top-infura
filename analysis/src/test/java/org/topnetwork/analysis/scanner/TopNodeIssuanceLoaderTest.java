package org.topnetwork.analysis.scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.topnetwork.analysis.AnalysisApplicationTests;
import org.topnetwork.analysis.block.NodeInfoLoader;
import org.topnetwork.analysis.block.NodeIssuanceLoader;

/**
 * @author CasonCai
 * @since 2021/4/27 下午6:17
 **/
public class TopNodeIssuanceLoaderTest extends AnalysisApplicationTests {

    @Autowired
    NodeIssuanceLoader nodeIssuanceLoader;

    @Autowired
    NodeInfoLoader nodeInfoLoader;

    @Test
    public void testNodeIssuance(){
        nodeIssuanceLoader.loadNextIssuance();
    }

    @Test
    public void testNodeInfo(){
        nodeInfoLoader.loadAllNodeInfo();
        nodeInfoLoader.loadElectInfo();
    }

}
