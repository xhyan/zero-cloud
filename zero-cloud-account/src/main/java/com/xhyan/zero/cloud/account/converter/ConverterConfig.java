package com.xhyan.zero.cloud.account.converter;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.exception.MappingException;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.Rule;
import com.xhyan.zero.cloud.account.utils.AnnotationUtil;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 实体转换器的配置类
 *
 * @author xhyan
 */
@Configuration
@EnableAutoConfiguration
@AutoConfigureBefore(value = Convert.class)
@EnableConfigurationProperties(ConverterProperties.class)
@ConditionalOnProperty(value = "zero.converter.enable", havingValue = "true")
public class ConverterConfig {


    @Autowired
    private ConverterProperties converterProperties;

    @PostConstruct
    @SneakyThrows({InstantiationException.class, IllegalAccessException.class})
    public void init() {
        //扫描并获取带@Convert注解的类
        List<String> scanPackages = converterProperties.getScanPackages();
        if (CollectionUtils.isEmpty(scanPackages)) {
            throw new MappingException("scanPackages in properties is empty. can not init Converter");
        }
        //获取配置的包路径下@Convert注解的类
        List<Class> classes = scanPackages.stream().flatMap(scanPackage -> AnnotationUtil.scanPackages(scanPackage, Convert.class).stream()).collect(Collectors.toList());
        classes.stream().filter(clazz -> clazz.isAnnotationPresent(Convert.class))
                .forEach(clazz -> {
                    //获取类上的@Convert注解
                    Convert convert = (Convert) clazz.getDeclaredAnnotation(Convert.class);
                    //得到目标类
                    Class<?> target = convert.target();
                    ClassMapBuilder aToBBuilder = MapperFactory.getCopyByRefMapper().mapClass(clazz, target).mapOnNull(converterProperties.isMapOnNull());//映射需要转换的类
                    Class<? extends AtoBMapping> mapping = convert.mapping();
                    if (!mapping.equals(AtoBMapping.class) && mapping.isAssignableFrom(AtoBMapping.class)) {
                        //定义了自定义映射，直接走自定义映射
                        aToBBuilder.customMapping(mapping.newInstance());
                    } else {
                        List<String> aIgnores = Lists.newArrayList();
                        Arrays.stream(clazz.getDeclaredFields())
                                .filter(field -> field.isAnnotationPresent(Rule.class))
                                .forEach(field -> {
                                    //只留下带有Rule注解的属性
                                    Rule rule = field.getAnnotation(Rule.class);
                                    if (rule.ignore()) {
                                        aIgnores.add(field.getName());
                                        return;
                                    }
                                    //注解中了设置targetField，直接取。若未设置，目标属性跟当前属性名称取相同值
                                    String targetField = Optional.ofNullable(rule.targetField()).orElse(field.getName());
                                    Class<? extends Transformer> transClass = rule.transformer();
                                    if (!transClass.equals(Transformer.class) && transClass.isAssignableFrom(Transformer.class)) {
                                        aToBBuilder.field(field.getName(), targetField, transClass.newInstance());
                                    } else {
                                        aToBBuilder.field(field.getName(), targetField);
                                    }

                                });
                        //设置映射器忽略的字段
                        if (!CollectionUtils.isEmpty(aIgnores)) {
                            aToBBuilder.exclude(aIgnores.toArray(new String[aIgnores.size()]));
                        }
                    }
                    //映射器注册
                    aToBBuilder.register();
                });
        MapperFactory.getCopyByRefMapper();
    }

    /**
     * 类自定义映射规则
     *
     * @param mapBuilder
     * @param mapping
     */
    private void customMapping(ClassMapBuilder mapBuilder, Class<? extends AtoBMapping> mapping) {
        try {
            mapBuilder.customMapping(mapping.newInstance()).register();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 属性自定义转换规则
     *
     * @param mapBuilder
     * @param field
     * @param targetField
     * @param transClass
     */
    private void fieldCustomTransformer(ClassMapBuilder mapBuilder, String field, String targetField, Class<? extends Transformer> transClass) {
        try {
            mapBuilder.field(field, targetField, transClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
