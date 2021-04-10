
DROP TABLE IF EXISTS `log_login_log`;
CREATE TABLE `log_login_log` (
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `type` varchar(255) NOT NULL COMMENT '登陆类型',
  `loginTime` datetime NOT NULL COMMENT '时间',
  `accessId` varchar(32) NOT NULL COMMENT '访问ID',
  `accessKey` varchar(64) NOT NULL COMMENT '访问Key',
  `source` enum('WEB','ANDROID','IOS') NOT NULL COMMENT '来源',
  `requestIp` varchar(255) NOT NULL COMMENT '请求IP',
  `invalid` enum('true','false') NOT NULL COMMENT '是否失效',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_accessId` (`accessId`) USING BTREE,
  KEY `normal_userId` (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆日志';

DROP TABLE IF EXISTS `log_operator_log`;
CREATE TABLE `log_operator_log` (
  `accessId` varchar(32) NOT NULL COMMENT '访问ID',
  `action` varchar(255) NOT NULL COMMENT '请求Action、URI',
  `requestIP` varchar(255) NOT NULL COMMENT '请求IP地址',
  `localIP` varchar(15) NOT NULL COMMENT '本机IP地址',
  `serverTime` datetime NOT NULL COMMENT '服务器时间',
  `requestContent` mediumtext NOT NULL COMMENT '请求主体内容',
  `responseContent` varchar(255) NOT NULL COMMENT '响应日志内容',
  `workerId` int(11) NOT NULL COMMENT '所属工作机器索引',
  `dataCenterId` int(11) NOT NULL COMMENT '所在数据中心索引',
  `requestLogNo` varchar(32) NOT NULL COMMENT '日志编号',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `method` varchar(255) NOT NULL COMMENT '请求方法',
  `name` varchar(255) NOT NULL COMMENT '应用名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_requestLogNo` (`requestLogNo`) USING BTREE,
  KEY `normal_accessId` (`accessId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Table structure for rec_account
-- ----------------------------
DROP TABLE IF EXISTS `rec_account`;
CREATE TABLE `rec_account` (
  `type` enum('ACCOUNT_PREFIX','SUB_ACCOUNT_PREFIX','SYS_BEACON_CONTRACT_PREFIX','SYS_ZEC_CONTRACT_PREFIX','SYS_SHARDING_CONTRACT_PREFIX','USER_CONTRACT_PREFIX','NODE','OTHER') NOT NULL COMMENT '账户类型',
  `address` varchar(255) NOT NULL COMMENT '账户地址',
  `balance` varchar(255) NOT NULL COMMENT '账户余额',
  `lockBalance` varchar(255) NOT NULL COMMENT '锁定金额',
  `txNum` varchar(255) NOT NULL COMMENT '交易数',
  `shard` int(11) NOT NULL COMMENT '所属分片',
  `gasTotal` varchar(255) NOT NULL COMMENT '总gas',
  `gasUnUse` varchar(255) NOT NULL COMMENT '未使用gas ，available_gas',
  `diskStakedToken` varchar(255) NOT NULL COMMENT 'DISK总质押',
  `gasStakedToken` varchar(255) NOT NULL COMMENT 'GAS总质押',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `chainZoneType` enum('CHAIN_ZONE_CONSENSUS_INDEX','CHAIN_ZONE_BEACON_INDEX','CHAIN_ZONE_ZEC_INDEX') NOT NULL COMMENT 'zoneType',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `clusterId` int(11) NOT NULL DEFAULT '0' COMMENT 'clusterId',
  `zoneId` int(11) NOT NULL DEFAULT '0' COMMENT 'zoneId',
  `lockGas` varchar(255) NOT NULL DEFAULT '0' COMMENT '除发送方交易消耗的gas，单位Tgas',
  `burnedToken` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户所有已经销毁的TOP token，单位uTOP',
  `unlockGasStaked` varchar(255) NOT NULL DEFAULT '0' COMMENT '解锁中的兑换gas的TOP token',
  `lockDepositBalance` varchar(255) NOT NULL DEFAULT '0' COMMENT '扣除发送方交易保证金，单位uTOP',
  `unlockDiskStaked` varchar(255) NOT NULL DEFAULT '0' COMMENT '解锁中的兑换disk的TOP token',
  `latestUnitHeight` varchar(255) NOT NULL DEFAULT '0' COMMENT '新共识成功的交易的unit block高度',
  `latestTxHashXxhash64` varchar(255) NOT NULL COMMENT '最新共识成功的交易xx64hash',
  `voteStakedToken` varchar(255) NOT NULL DEFAULT '0' COMMENT '兑换选票锁定的TOP token，单位uTOP',
  `nonce` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户最新共识成功的交易序号，唯一',
  `unusedVoteAmount` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户未使用选票数量',
  `latestTxHash` varchar(255) NOT NULL COMMENT '最新共识成功的交易hash',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_address` (`address`) USING BTREE,
  KEY `normal_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rec_block_stream
-- ----------------------------
DROP TABLE IF EXISTS `rec_block_stream`;
CREATE TABLE `rec_block_stream` (
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for rec_contract
-- ----------------------------
DROP TABLE IF EXISTS `rec_contract`;
CREATE TABLE `rec_contract` (
  `type` enum('ACCOUNT_PREFIX','SUB_ACCOUNT_PREFIX','SYS_BEACON_CONTRACT_PREFIX','SYS_ZEC_CONTRACT_PREFIX','SYS_SHARDING_CONTRACT_PREFIX','USER_CONTRACT_PREFIX','NODE','OTHER') NOT NULL COMMENT '账户类型',
  `address` varchar(255) NOT NULL COMMENT '账户地址',
  `balance` varchar(255) NOT NULL COMMENT '账户余额',
  `lockBalance` varchar(255) NOT NULL COMMENT '锁定金额',
  `txNum` varchar(255) NOT NULL COMMENT '交易数',
  `shard` int(11) NOT NULL COMMENT '所属分片',
  `gasTotal` varchar(255) NOT NULL COMMENT '总gas',
  `gasUnUse` varchar(255) NOT NULL COMMENT '未使用gas ,对应grpc available_gas',
  `diskStakedToken` varchar(255) NOT NULL COMMENT 'DISK总质押',
  `gasStakedToken` varchar(255) NOT NULL COMMENT 'GAS总质押',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `byteCode` text COMMENT '合约代码',
  `creator` varchar(255) DEFAULT NULL COMMENT '合约父账户，部署合约的账户',
  `chainZoneType` enum('CHAIN_ZONE_CONSENSUS_INDEX','CHAIN_ZONE_BEACON_INDEX','CHAIN_ZONE_ZEC_INDEX') NOT NULL COMMENT 'zoneType',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `clusterId` int(11) NOT NULL DEFAULT '0' COMMENT 'clusterId',
  `zoneId` int(11) NOT NULL DEFAULT '0' COMMENT 'zoneId',
  `lockGas` varchar(255) NOT NULL DEFAULT '0' COMMENT '除发送方交易消耗的gas，单位Tgas',
  `burnedToken` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户所有已经销毁的TOP token，单位uTOP',
  `unlockGasStaked` varchar(255) NOT NULL DEFAULT '0' COMMENT '解锁中的兑换gas的TOP token',
  `lockDepositBalance` varchar(255) NOT NULL DEFAULT '0' COMMENT '扣除发送方交易保证金，单位uTOP',
  `unlockDiskStaked` varchar(255) NOT NULL DEFAULT '0' COMMENT '解锁中的兑换disk的TOP token',
  `latestUnitHeight` varchar(255) NOT NULL DEFAULT '0' COMMENT '新共识成功的交易的unit block高度',
  `latestTxHashXxhash64` varchar(255) NOT NULL COMMENT '最新共识成功的交易xx64hash',
  `voteStakedToken` varchar(255) NOT NULL DEFAULT '0' COMMENT '兑换选票锁定的TOP token，单位uTOP',
  `nonce` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户最新共识成功的交易序号，唯一',
  `unusedVoteAmount` varchar(255) NOT NULL DEFAULT '0' COMMENT '该账户未使用选票数量',
  `latestTxHash` varchar(255) NOT NULL COMMENT '最新共识成功的交易hash',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_address` (`address`) USING BTREE,
  KEY `normal_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for rec_election_info
-- ----------------------------
DROP TABLE IF EXISTS `rec_election_info`;
CREATE TABLE `rec_election_info` (
  `edge` int(11) NOT NULL COMMENT 'edge节点数量',
  `archive` int(11) NOT NULL COMMENT 'archive节点数量',
  `auditor` int(11) NOT NULL COMMENT 'auditor节点数量',
  `validator` int(11) NOT NULL COMMENT 'validator节点数量',
  `root_beacon` int(11) NOT NULL COMMENT 'root_beacon节点数量',
  `sub_beacon` int(11) NOT NULL COMMENT 'sub_beacon节点数量',
  `edgeHeight` varchar(255) NOT NULL COMMENT 'edge节点本轮块高',
  `archiveHeight` varchar(255) NOT NULL COMMENT 'archive节点本轮块高',
  `auditorHeight` varchar(255) NOT NULL COMMENT 'auditor节点本轮块高',
  `validatorHeight` varchar(255) NOT NULL COMMENT 'validator节点本轮块高',
  `root_beaconHeight` varchar(255) NOT NULL COMMENT 'root_beacon节点本轮块高',
  `sub_beaconHeight` varchar(255) NOT NULL COMMENT 'sub_beacon节点本轮块高',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for rec_node_info
-- ----------------------------
DROP TABLE IF EXISTS `rec_node_info`;
CREATE TABLE `rec_node_info` (
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rec_node_reward_record`;
CREATE TABLE `rec_node_reward_record` (
  `nodeAddress` varchar(255) NOT NULL COMMENT '节点地址',
  `accumulated` varchar(255) NOT NULL DEFAULT '0' COMMENT '奖励总额，单位uTOP',
  `lastClaimTime` varchar(255) NOT NULL DEFAULT '0' COMMENT '上次领取奖励的时钟高度',
  `unclaimed` varchar(255) NOT NULL DEFAULT '0' COMMENT '未领取的奖励，单位uTOP',
  `issueTime` varchar(255) NOT NULL DEFAULT '0' COMMENT '发放奖励时钟高度',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_nodeAddress_issueTime` (`nodeAddress`,`issueTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点奖励记录表';


-- ----------------------------
-- Table structure for rec_shard
-- ----------------------------
DROP TABLE IF EXISTS `rec_shard`;
CREATE TABLE `rec_shard` (
  `shardId` varchar(255) NOT NULL COMMENT '分片Id',
  `clusterId` varchar(255) NOT NULL COMMENT 'clusterId',
  `zoneId` varchar(255) NOT NULL COMMENT 'zoneId',
  `blockSize` int(11) NOT NULL COMMENT '分片下单元块数',
  `accountSize` int(11) NOT NULL COMMENT '分片下账户数',
  `transactionSize` int(11) NOT NULL COMMENT '分片下交易数量',
  `tps` bigint(20) NOT NULL COMMENT '当前tps',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_shardId` (`shardId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rec_transaction`;
CREATE TABLE `rec_transaction` (
  `xhash` varchar(255) NOT NULL COMMENT 'hash',
  `txType` enum('create_user_account','create_contract_account','run_contract','transfer','alias_name','set_account_keys','lock_token','unlock_token','create_sub_account','vote','abolish_vote','pledge_token_tgas','redeem_token_tgas','pledge_token_disk','redeem_token_disk','pledge_token_vote','redeem_token_vote') NOT NULL COMMENT '交易类型',
  `xtimestamp` bigint(20) NOT NULL COMMENT '交易时间',
  `xfrom` varchar(255) NOT NULL COMMENT '发送人',
  `xto` varchar(255) NOT NULL COMMENT '接收人',
  `amount` varchar(255) NOT NULL COMMENT '数量',
  `xnote` varchar(255) DEFAULT NULL COMMENT '备注',
  `last_tx_nonce` varchar(255) DEFAULT NULL COMMENT '上笔交易序号',
  `tx_len` varchar(255) DEFAULT NULL COMMENT '交易体大小',
  `xstatus` varchar(255) DEFAULT NULL COMMENT '状态',
  `shardFrom` int(11) NOT NULL COMMENT '分片from',
  `shardTo` int(11) NOT NULL COMMENT '分片to',
  `used_deposit` varchar(255) NOT NULL DEFAULT '0' COMMENT 'used_deposit',
  `gasUsed` bigint(20) NOT NULL DEFAULT '0' COMMENT 'gas used',
  `diskUsed` bigint(20) NOT NULL DEFAULT '0' COMMENT 'disk used',
  `txDeposit` varchar(255) DEFAULT NULL COMMENT '交易保证金，单位uTOP',
  `chainZoneType` enum('CHAIN_ZONE_CONSENSUS_INDEX','CHAIN_ZONE_BEACON_INDEX','CHAIN_ZONE_ZEC_INDEX') NOT NULL COMMENT 'zoneType',
  `funcName` varchar(255) DEFAULT NULL COMMENT '执行合约的函数名',
  `para` text COMMENT '执行合约的参数',
  `address` varchar(255) DEFAULT NULL COMMENT '账号地址',
  `tokenName` varchar(255) DEFAULT NULL COMMENT '币名',
  `tgasLimit` varchar(255) DEFAULT NULL COMMENT '一次合约调用中，合约账户愿意支付的最大tgas',
  `code` text COMMENT '合约代码',
  `voteNum` varchar(255) DEFAULT NULL COMMENT '兑票数',
  `lockDuration` varchar(255) DEFAULT NULL COMMENT '票的锁定时长（天）',
  `accountKey` varchar(255) DEFAULT NULL COMMENT '属性的key',
  `keyValue` varchar(255) DEFAULT NULL COMMENT '设置的值',
  `version` varchar(255) DEFAULT NULL COMMENT '版本',
  `unlockType` varchar(255) DEFAULT NULL COMMENT '解锁类型',
  `unlockValues` varchar(255) DEFAULT NULL COMMENT '解锁类型对应的值',
  `params` varchar(255) DEFAULT NULL COMMENT '序列化的锁定上下文',
  `lockTranHash` varchar(255) DEFAULT NULL COMMENT '锁定交易的hash',
  `signatures` varchar(255) DEFAULT NULL COMMENT '签名',
  `name` varchar(255) DEFAULT NULL COMMENT '别名',
  `recv_used_deposit` varchar(255) DEFAULT NULL COMMENT 'recv_used_deposit',
  `recv_used_gas` varchar(255) DEFAULT NULL COMMENT 'recv_used_gas',
  `recv_used_disk` varchar(255) DEFAULT NULL COMMENT 'recv_used_disk',
  `send_used_deposit` varchar(255) DEFAULT NULL COMMENT 'send_used_deposit',
  `send_used_gas` varchar(255) DEFAULT NULL COMMENT 'send_used_gas',
  `send_used_disk` varchar(255) DEFAULT NULL COMMENT 'send_used_disk',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sender_action_param` text COMMENT 'sender_action_param',
  `receiver_action_param` text COMMENT 'receiver_action_param',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_xhash` (`xhash`) USING BTREE,
  KEY `normal_txType` (`txType`) USING BTREE,
  KEY `normal_xtimestamp` (`xtimestamp`) USING BTREE,
  KEY `normal_xfrom` (`xfrom`) USING BTREE,
  KEY `normal_xto` (`xto`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rec_unit_block`;
CREATE TABLE `rec_unit_block` (
  `xheight` bigint(20) NOT NULL COMMENT '单元块高度',
  `account` varchar(255) NOT NULL COMMENT '产生unit块的账号地址',
  `xhash` varchar(255) NOT NULL COMMENT '单元块hash',
  `zoneId` int(11) NOT NULL COMMENT '单元块zoneId',
  `tableId` int(11) NOT NULL COMMENT '单元块tableId',
  `shardId` int(11) NOT NULL COMMENT '单元块shardId',
  `timestamp` bigint(20) NOT NULL COMMENT '单元块出块时间',
  `auditor` varchar(255) DEFAULT NULL COMMENT '出块auditor账号地址',
  `validator` varchar(255) DEFAULT NULL COMMENT '出块validator账号地址',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `clusterId` int(11) NOT NULL COMMENT '单元块clusterId',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_xhash` (`xhash`) USING BTREE,
  UNIQUE KEY `unique_xheight_account` (`xheight`,`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='unit块表';

DROP TABLE IF EXISTS `sys_secret_key`;
CREATE TABLE `sys_secret_key` (
  `accessId` varchar(32) NOT NULL COMMENT '访问ID',
  `accessKey` varchar(64) NOT NULL COMMENT '密钥',
  `type` int(11) NOT NULL COMMENT '类型',
  `invalidTime` datetime NOT NULL COMMENT '失效时间',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  `id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_accessId` (`accessId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密钥';

-- ----------------------------
-- Records of sys_secret_key
-- ----------------------------
BEGIN;
INSERT INTO `sys_secret_key` VALUES ('8412f654f8662d033111fc453edc5b63', 'c4jWPrC8mdRC+Nt3ftdwDDi564O3L0+FqMjRRHwHigBwmmoSZB7Pug==', 0, '2021-01-01 00:00:00', '开发', 614072878689681408);
COMMIT;
-- ----------------------------
-- Table structure for top_node_map
-- ----------------------------
DROP TABLE IF EXISTS `top_node_map`;
CREATE TABLE `top_node_map` (
  `ipAddress` varchar(255) NOT NULL COMMENT 'ip',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `id` bigint(20) NOT NULL COMMENT '主键',
  `country` varchar(255) DEFAULT NULL COMMENT '国家（en）',
  `province` varchar(255) DEFAULT NULL COMMENT '省（en）',
  `cityZh` varchar(255) DEFAULT NULL COMMENT '中文城市名',
  `city` varchar(255) DEFAULT NULL COMMENT '城市（en）',
  `latitude` double(22,4) NOT NULL DEFAULT '0.0000' COMMENT '纬度',
  `provinceZh` varchar(255) DEFAULT NULL COMMENT '中文省名',
  `countryZh` varchar(255) DEFAULT NULL COMMENT '中文国家名',
  `longitude` double(22,4) NOT NULL DEFAULT '0.0000' COMMENT '经度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_ipAddress` (`ipAddress`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of top_node_map
-- ----------------------------
INSERT INTO `top_node_map` VALUES ('122.225.228.122', '2020-08-18 19:57:27', '2020-08-18 21:46:13', 745370800210575360, 'China', 'Zhejiang', '杭州', 'Hangzhou', 30.2940, '浙江省', '中国', 120.1619);
INSERT INTO `top_node_map` VALUES ('52.43.188.13', '2020-08-19 10:26:48', '2020-08-19 10:26:48', 745589577678323712, 'United States', 'Oregon', NULL, 'Boardman', 45.8491, '俄勒冈州', '美国', -119.7143);