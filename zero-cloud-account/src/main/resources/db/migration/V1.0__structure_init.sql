DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `login_name` varchar(128) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(128) DEFAULT NULL COMMENT '登录密码(不可逆加密)',
  `trade_pwd` varchar(128) DEFAULT NULL COMMENT '交易密码(不可逆加密)',
  `email` varchar(255) DEFAULT NULL COMMENT ' 账号绑定邮箱',
  `email_backup` varchar(255) DEFAULT NULL COMMENT ' 账号绑定备用邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '账号绑定手机号',
  `type` tinyint(4) NOT NULL COMMENT '账户类型(1个人账户；2企业账户)',
  `property` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账户性质（1资金账户，3商户账户，4担保账户）',
  `pay_flag` bigint(20) NOT NULL DEFAULT '0' COMMENT '支付功能控制标志',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '账户状态：1正常,2受限,3冻结,4注销',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `type_user_id` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户主表';