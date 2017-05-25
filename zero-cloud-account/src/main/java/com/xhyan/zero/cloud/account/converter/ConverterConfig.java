package com.xhyan.zero.cloud.account.converter;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.CopyByRefMapper;
import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.ConvertRule;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Bean
    public CopyByRefMapper CopyByRefMapper() {
        CopyByRefMapper mapper = new CopyByRefMapper();
        //扫描并获取带@Convert注解的类
        List<Class> classes = this.scanPackages();
        classes.stream().filter(clazz -> clazz.isAnnotationPresent(Convert.class)).peek(clazz -> {
            //获取类上的@Convert注解
            Convert convert = (Convert) clazz.getAnnotation(Convert.class);
            //得到目标类
            Class<?> target = convert.target();
            ClassMapBuilder aToBBuilder = mapper.mapClass(clazz, target).mapOnNull(converterProperties.isMapOnNull());//映射需要转换的类
            final boolean unidirection = convert.unidirection() || converterProperties.isUnidirection();
            ClassMapBuilder bToABuilder;
            if (unidirection) {
                bToABuilder = mapper.mapClass(target, clazz).mapOnNull(converterProperties.isMapOnNull());
            }
            List<String> aIgnores = Lists.newArrayList();
            Arrays.stream(clazz.getFields())
                    .filter(field -> {
                        if (field.isAnnotationPresent(ConvertRule.class) && !field.getAnnotation(ConvertRule.class).ignore()) {
                            aIgnores.add(field.getName());
                            return true;
                        }
                        return false;
                    })
                    .peek(field -> {
                        ConvertRule rule = field.getAnnotation(ConvertRule.class);
                        if (StringUtils.isNotBlank(rule.field())) {
                            //设置自定义的映射规则
                            aToBBuilder.field(field.getName(), rule.field());
                        }
                    });
                //设置映射器忽略的字段
                aToBBuilder.exclude((String[]) aIgnores.stream().toArray());
        });
        return mapper;
    }

    private List<Class> scanPackages() {
        return new ArrayList<>();
    }
}
