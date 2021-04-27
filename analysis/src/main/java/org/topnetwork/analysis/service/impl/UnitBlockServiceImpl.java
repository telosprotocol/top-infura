package org.topnetwork.analysis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.topnetwork.analysis.service.AccountService;
import org.topnetwork.analysis.service.UnitBlockService;
import org.topnetwork.grpclib.pojo.stream.UnitsBlockMap;
import org.topnetwork.grpclib.pojo.unit.UnitBlockResult;
import org.topnetwork.grpclib.xrpc.TopGrpcClient;

import java.util.HashMap;

public class UnitBlockServiceImpl implements UnitBlockService {

    @Value("${top.node.rediskey.prefix}")
    public String topNodeRedisPrefix;


    @Value("${top.grpcip}")
    private String ip;
    @Value("${top.grpcport}")
    private int port;

    private static final Logger LOG = LoggerFactory.getLogger(UnitBlockServiceImpl.class);
    @Autowired
    private AccountService accountService;


    @Override
    public void saveUnitBlock(HashMap<String, UnitsBlockMap> units) {

        if (units == null) return;

        for (String account : units.keySet()) {
            UnitBlockResult unitBlockResult = queryAccountAndHeight(account, units.get(account).getUnit_height());
            if (!ObjectUtils.isEmpty(unitBlockResult)) {
                return;
            }
            unitBlockResult = new UnitBlockResult();
            UnitBlockResult blockByAddressAndHeight = TopGrpcClient.getInstance(ip, port).getUnitBlock(account, (long) units.get(account).getUnit_height());

            if (blockByAddressAndHeight == null) continue;
            if (blockByAddressAndHeight.getValue() == null) continue;
            org.topnetwork.grpclib.pojo.unit.Value value = blockByAddressAndHeight.getValue();
//            unitBlock.setAccount(value.getOwner());
//
//            Account account1 = accountService.getAccOrSync(value.getOwner());
//            unitBlock.setAuditor(value.getHeader().getAuditor());
//            unitBlock.setHash(value.getHash());
//            unitBlock.setHeight((long) units.get(account).getUnit_height());
//            unitBlock.setShardId(account1.getShard());
//            unitBlock.setTableId(value.getTable_id());
//            unitBlock.setTimestamp(value.getTimestamp());
//            unitBlock.setZoneId(account1.getZoneId());
//            unitBlock.setClusterId(account1.getClusterId());
//            unitBlock.setCreatedDate(new Date());
//            unitBlock.setModifiedDate(new Date());
//            unitBlock.setValidator(value.getHeader().getValidator());

        }

    }

    private UnitBlockResult queryAccountAndHeight(String account, Long height) {
        return null;
    }

    private String shardStr(Integer shardId, boolean isZh) {
        if (shardId >= 64 && shardId < 127) {
            return isZh ? "验证网络 " + (shardId - 63) : "validate Network " + (shardId - 63);
        }
        return isZh ? "审计网络 " + shardId : "Audit Network " + shardId;
    }
}
