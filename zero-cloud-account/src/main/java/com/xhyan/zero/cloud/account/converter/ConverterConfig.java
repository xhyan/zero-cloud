package com.xhyan.zero.cloud.account.converter;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.CopyByRefMapper;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.ConvertRule;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 实体转换器的配置类
 *
 * @author xhyan
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(ConverterProperties.class)
@ConditionalOnProperty
public class ConverterConfig {

    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Autowired
    private ConverterProperties converterProperties;

    @Bean
    public CopyByRefMapper CopyByRefMapper() {
        CopyByRefMapper mapper = new CopyByRefMapper();
        //扫描并获取带@Convert注解的类
        List<Class> classes = this.scanPackages();
        classes.stream().filter(clazz -> clazz.isAnnotationPresent(Convert.class)).peek((Class clazz) -> {
            //获取类上的@Convert注解
            Convert convert = (Convert) clazz.getAnnotation(Convert.class);
            //得到目标类
            Class<?> target = convert.target();
            ClassMapBuilder aToBBuilder = mapper.mapClass(clazz, target).mapOnNull(converterProperties.isMapOnNull());//映射需要转换的类
            Class<? extends AtoBMapping> mapping = convert.mapping();
            if (!mapping.equals(AtoBMapping.class) && mapping.isAssignableFrom(AtoBMapping.class)) {
                try {
                    aToBBuilder.customMapping(mapping.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                List<String> aIgnores = Lists.newArrayList();
                Arrays.stream(clazz.getFields())
                        .filter(field -> {
                            //过滤调自定已注解ConvertRule中，ignore为true的属性
                            if (field.isAnnotationPresent(ConvertRule.class) && !field.getAnnotation(ConvertRule.class).ignore()) {
                                aIgnores.add(field.getName());
                                return true;
                            }
                            return false;
                        })
                        .peek(field -> {
                            ConvertRule rule = field.getAnnotation(ConvertRule.class);
                            //注解中了设置targetField，直接取。若未设置，目标属性跟当前属性名称取相同值
                            String targetField = Optional.ofNullable(rule.targetField()).orElse(field.getName());
                            Class<? extends Transformer> transClass = rule.transformer();
                            if (!transClass.equals(Transformer.class) && transClass.isAssignableFrom(Transformer.class)) {
                                try {
                                    aToBBuilder.field(field.getName(), targetField, transClass.newInstance());
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                aToBBuilder.field(field.getName(), targetField);
                            }
                        });
                //设置映射器忽略的字段
                aToBBuilder.exclude((String[]) aIgnores.stream().toArray());
                aToBBuilder.register();
            }
        });

        return mapper;
    }

    private List<Class> scanPackages() {
        String packagename = "";
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(packagename) + RESOURCE_PATTERN;

        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources(pattern);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
        List<Class> classList = Lists.newArrayList();
        AnnotationTypeFilter typeFilter = new AnnotationTypeFilter(Convert.class, false);
        Arrays.stream(resources).filter(resource -> resource.isReadable()).forEach(resource -> {
            MetadataReader reader = null;
            try {
                reader = readerFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                if (typeFilter.match(reader, readerFactory)){
                    classList.add(Class.forName(className));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return classList;
    }


}
