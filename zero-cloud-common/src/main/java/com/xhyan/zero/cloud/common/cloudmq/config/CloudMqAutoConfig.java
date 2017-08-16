package com.xhyan.zero.cloud.common.cloudmq.config;

import com.cloudzone.cloudmq.api.open.base.Consumer;
import com.cloudzone.cloudmq.api.open.base.MsgListener;
import com.cloudzone.cloudmq.api.open.base.Producer;
import com.cloudzone.cloudmq.api.open.bean.ConsumerBean;
import com.cloudzone.cloudmq.api.open.bean.ProducerBean;
import com.cloudzone.cloudmq.api.open.bean.Subscription;
import com.cloudzone.cloudmq.common.PropertiesConst;
import com.cloudzone.cloudmq.common.PropertiesConst.Keys;
import com.xhyan.zero.cloud.common.cloudmq.annotations.CloudMqListener;
import com.xhyan.zero.cloud.common.cloudmq.utils.AnnotationUtil;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanliwei on 2017/7/18.
 */
@Configuration
@EnableConfigurationProperties(CloudMqProducerProperties.class)
public class CloudMqAutoConfig {

  @Autowired
  private CloudMqProducerProperties cloudMqProducerProperties;

  @Bean
  @ConditionalOnMissingBean(Producer.class)
  @ConditionalOnProperty(
      prefix = "cloud.mq.producer",
      value = {"enable"},
      havingValue = "true"
  )
  public Producer producer() {
    Properties properties = new Properties();
    properties.setProperty(PropertiesConst.Keys.ProducerGroupId,
        cloudMqProducerProperties.getProducerGroupId());
    properties.setProperty(PropertiesConst.Keys.TOPIC_NAME_AND_AUTH_KEY,
        cloudMqProducerProperties.getTopic() + ":" + cloudMqProducerProperties.getAuthKey());
    ProducerBean producerBean = new ProducerBean();
    producerBean.setProperties(properties);
    producerBean.start();
    return producerBean;
  }

  @Bean
  @ConditionalOnMissingBean(Consumer.class)
  @ConditionalOnProperty(
      prefix = "cloud.mq.consumer",
      value = {"enable"},
      havingValue = "true"
  )
  public Consumer consumer() {
    ConsumerBean consumerBean = new ConsumerBean();

    Properties properties = new Properties();
    properties.setProperty(Keys.ConsumerGroupId,
        cloudMqProducerProperties.getConsumerGroupId());
    properties.setProperty(PropertiesConst.Keys.TOPIC_NAME_AND_AUTH_KEY,
        cloudMqProducerProperties.getTopic() + ":" + cloudMqProducerProperties.getAuthKey());
    consumerBean.setProperties(properties);

    String packages = cloudMqProducerProperties.getConsumerListenerPackages();
    List<Class> classList = AnnotationUtil.scanPackages(CloudMqListener.class, packages.split(","));
    Map<Subscription, MsgListener> listenerMap = new HashMap<>();
    classList.stream().filter(clazz -> clazz.isAnnotationPresent(CloudMqListener.class) && clazz
        .isAssignableFrom(MsgListener.class))
        .forEach(clazz -> {
          CloudMqListener listener = (CloudMqListener) clazz
              .getDeclaredAnnotation(CloudMqListener.class);
          Subscription subscription = new Subscription();
          subscription.setTopic(listener.topic());
          subscription.setExpression(listener.expression());
          try {
            listenerMap.put(subscription, (MsgListener) clazz.newInstance());
          } catch (InstantiationException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        });
    consumerBean.setSubscriptionTable(listenerMap);
    consumerBean.start();
    return consumerBean;
  }

}
