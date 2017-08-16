package com.xhyan.zero.cloud.common.cloudmq.annotations;

/**
 * 消息队列监听器
 */
public @interface CloudMqListener {
   String topic();
   String expression();
}
