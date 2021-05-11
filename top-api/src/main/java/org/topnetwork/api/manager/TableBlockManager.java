package org.topnetwork.api.manager;

import org.topnetwork.common.entity.TopTableBlock;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/4/30 10:28 上午
 **/
public interface TableBlockManager {

    List<Long> getLatestTableBlockHeight();

    TopTableBlock getTableBlock(String address, Long height);


}
