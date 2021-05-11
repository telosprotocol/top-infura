package org.topnetwork.common.service;

import org.topnetwork.common.entity.TopNodeElection;
import com.baomidou.mybatisplus.extension.service.IService;
import org.topnetwork.common.entity.TopNodeInfo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-05-08
 */
public interface TopNodeElectionService extends IService<TopNodeElection> {

    List<TopNodeElection> getElectedNodes(List<String> types);

    boolean electeNode(String address, String nodeType, LocalDateTime electedTime, Long electedBlockHeight);

    void loseElectNode(Collection<TopNodeElection> topNodeInfos);


}
