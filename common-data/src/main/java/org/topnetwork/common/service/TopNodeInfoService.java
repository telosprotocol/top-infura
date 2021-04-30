package org.topnetwork.common.service;

import org.topnetwork.common.entity.TopNodeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CasonCai
 * @since 2021-04-20
 */
public interface TopNodeInfoService extends IService<TopNodeInfo> {

    Set<String> getAllNodeAddress();

    boolean udpateNodeRewardInfo(TopNodeInfo topNodeInfo);

    boolean updateNodeInfo(TopNodeInfo topNodeInfo);

    boolean electNode(String address, String type,BigInteger version, LocalDateTime lastElectedTime,  Long lastElectedBlockHeight);

    List<TopNodeInfo> getElectedNodeByTypes(Collection<String> types);

    void loseElectNode(Collection<TopNodeInfo> topNodeInfos);



}
