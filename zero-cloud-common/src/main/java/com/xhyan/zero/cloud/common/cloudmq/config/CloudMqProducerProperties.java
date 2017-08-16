package com.xhyan.zero.cloud.common.cloudmq.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 消息队列配置属性
 */
@ConfigurationProperties(prefix = "cloud.mq")
public class CloudMqProducerProperties {

  private String topic;
  private String authKey;
  private String producerGroupId;
  private boolean producerEnable = false;
  private String consumerGroupId;
  private boolean consumerEnable = false;
  private String consumerListenerPackages;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }

  public String getProducerGroupId() {
    return producerGroupId;
  }

  public void setProducerGroupId(String producerGroupId) {
    this.producerGroupId = producerGroupId;
  }

  public boolean isProducerEnable() {
    return producerEnable;
  }

  public void setProducerEnable(boolean producerEnable) {
    this.producerEnable = producerEnable;
  }

  public String getConsumerGroupId() {
    return consumerGroupId;
  }

  public void setConsumerGroupId(String consumerGroupId) {
    this.consumerGroupId = consumerGroupId;
  }

  public boolean isConsumerEnable() {
    return consumerEnable;
  }

  public void setConsumerEnable(boolean consumerEnable) {
    this.consumerEnable = consumerEnable;
  }

  public String getConsumerListenerPackages() {
    return consumerListenerPackages;
  }

  public void setConsumerListenerPackages(String consumerListenerPackages) {
    this.consumerListenerPackages = consumerListenerPackages;
  }
}
