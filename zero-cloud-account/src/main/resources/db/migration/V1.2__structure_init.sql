DROP TABLE IF EXISTS `rewards_dispatch`;
CREATE TABLE `rewards_dispatch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `type` int(2) DEFAULT NULL COMMENT '奖励类型',
  `amount` decimal(10, 4) NOT NULL DEFAULT 0 COMMENT '数量',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `idx_account_type` (`account_id`, `type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖励发放';