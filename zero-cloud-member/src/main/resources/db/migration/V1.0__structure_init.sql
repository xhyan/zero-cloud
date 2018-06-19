DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `level` int(2) NOT NULL DEFAULT 0 COMMENT '会员等级',
  `points` bigint(20) NOT NULL DEFAULT 0 COMMENT '会员积分',
  `available_points` bigint(20) NOT NULL DEFAULT 0 COMMENT '会员可用积分',
  `number` varchar(20) DEFAULT NULL COMMENT '会员卡号',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '会员状态：1正常,4注销',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员信息表';