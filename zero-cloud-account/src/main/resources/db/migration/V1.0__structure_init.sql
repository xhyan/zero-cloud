DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `wallet_id` bigint(20) DEFAULT NULL COMMENT '钱包ID',
  `mobile` varchar(20) DEFAULT NULL COMMENT '账号绑定手机号',
  `login_name` varchar(128) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(128) DEFAULT NULL COMMENT '登录密码(不可逆加密)',
  `email` varchar(255) DEFAULT NULL COMMENT ' 账号绑定邮箱',
  `energy` int(8) DEFAULT NULL COMMENT '活力值',
  `mining` int(2) NOT NULL DEFAULT '0' COMMENT '是否开启挖矿:1 是,2 否 ',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '账户状态：1 正常,4 注销',
  `name` varchar(64) DEFAULT NULL COMMENT ' 姓名',
  `identity_card` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户主表';

DROP TABLE IF EXISTS `account_bind`;
CREATE TABLE `account_bind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `application` varchar(20) NOT NULL COMMENT '绑定/授权应用',
  `credentials` varchar(20) DEFAULT NULL COMMENT '绑定/授权应用凭证',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `idx_app` (`application`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户绑定信息';