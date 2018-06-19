DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `email` varchar(255) DEFAULT NULL COMMENT ' 账号绑定邮箱',
  `email_backup` varchar(255) NOT NULL COMMENT ' 账号绑定备用邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '账号绑定手机号',
  `type` int(2) NOT NULL COMMENT '账户类型(1个人账户；2企业账户)',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '账户状态：1正常,2受限,3冻结,4注销',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `type_user_id` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户主表';