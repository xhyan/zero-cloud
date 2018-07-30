DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `mobile` varchar(20) DEFAULT NULL COMMENT '账号绑定手机号',
  `type` int(2) DEFAULT NULL COMMENT '验证码类型',
  `code` varchar(64) DEFAULT NULL COMMENT '验证码',
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `idx_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信验证码';

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '任务名称',
  `type` int(2) DEFAULT NULL COMMENT '任务类型:1 内部  2 外部',
  `energy` int(8) DEFAULT NULL COMMENT '任务活力值',
  `status` int(2) DEFAULT NULL COMMENT '任务状态：1 有效  -1 无效',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务信息';

DROP TABLE IF EXISTS `account_task`;
CREATE TABLE `account_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `task_id` bigint(20) NOT NULL COMMENT '任务id',
  `energy` int(8) DEFAULT NULL COMMENT '任务活力值',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `extend` text COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `idx_account` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户任务对应关系';
