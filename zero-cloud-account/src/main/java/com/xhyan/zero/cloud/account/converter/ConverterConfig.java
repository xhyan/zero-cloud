package com.xhyan.zero.cloud.account.converter;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.CopyByRefMapper;
import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.exception.MappingException;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.ConvertRule;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
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
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.annotation.Annotation;
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

    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Autowired
    private ConverterProperties converterProperties;

    @PostConstruct
    public void init() {
        //扫描并获取带@Convert注解的类
        List<String> scanPackages = converterProperties.getScanPackages();
        if (CollectionUtils.isEmpty(scanPackages)) {
            throw new MappingException("scanPackages in properties is empty. can not init Converter");
        }
        //获取配置的包路径下@Convert注解的类
        List<Class> classes = scanPackages.stream().flatMap(scanPackage -> this.scanPackages(scanPackage).stream()).collect(Collectors.toList());
        classes.stream().filter(clazz -> clazz.isAnnotationPresent(Convert.class)).forEach((Class clazz) -> {
            //获取类上的@Convert注解
            Convert convert = (Convert) clazz.getAnnotation(Convert.class);
            //得到目标类
            Class<?> target = convert.target();
            ClassMapBuilder aToBBuilder = MapperFactory.getCopyByRefMapper().mapClass(clazz, target).mapOnNull(converterProperties.isMapOnNull());//映射需要转换的类
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
                Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ConvertRule.class))
                        .forEach(field -> {
                            ConvertRule rule = field.getAnnotation(ConvertRule.class);

                            if (rule.ignore()) {
                                aIgnores.add(field.getName());
                                return;
                            }
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
                if (!CollectionUtils.isEmpty(aIgnores)) {
                    aToBBuilder.exclude(aIgnores.toArray(new String[aIgnores.size()]));
                }
                aToBBuilder.register();
            }
        });
        MapperFactory.getCopyByRefMapper();
    }

    private List<Class> scanPackages(String packageName) {
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;

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
                if (typeFilter.match(reader, readerFactory)) {
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
