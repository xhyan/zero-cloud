package com.xhyan.zero.cloud.account.converter;

import com.baidu.unbiz.easymapper.CopyByRefMapper;
import com.google.common.collect.Lists;
import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.ConvertRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实体转换器的配置类
 *
 * @author xhyan
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(ConverterProperties.class)
public class ConverterConfig {

    @Autowired
    private ConverterProperties converterProperties;
    private Convert convert;

    @Bean
    public CopyByRefMapper CopyByRefMapper() {
        CopyByRefMapper mapper = new CopyByRefMapper();
        //扫描并获取带@Convert注解的类
        List<Class> classes = this.scanAnnotation();
        classes.forEach(item -> {
            //获取类上的@Convert注解
            Convert convert = (Convert) item.getAnnotation(Convert.class);
            Class<?> target = convert.target();


            List<String> ingoreFileds = Lists.newArrayList();
            Annotation[] convertRules = item.getAnnotationsByType(ConvertRule.class);
            Arrays.stream(convertRules)
                    .map(rule -> (ConvertRule) rule)
                    .collect(Collectors.toList())
                    .forEach(rule -> {
                    });


        });
        return mapper;
    }

    private List<Class> scanAnnotation() {
        return new ArrayList<>();
    }
}
