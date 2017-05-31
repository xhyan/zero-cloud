package com.xhyan.zero.cloud.account.utils;

import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 注解工具类
 */
public class AnnotationUtil {

    //需要扫描的资源路径
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    /**
     * 扫描package下面，并获取所有带有指定注解的类
     *
     * @param packageName
     * @return
     */
    @SneakyThrows
    public static List<Class> scanPackages(Class<? extends Annotation> annotation, String... packageName) {

        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
        AnnotationTypeFilter typeFilter = new AnnotationTypeFilter(annotation, false);
        List<Resource> resources = Arrays.stream(packageName).flatMap(packages -> {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(packages) + RESOURCE_PATTERN;
            return Arrays.stream(resolver.getResources(pattern));
        }).collect(Collectors.toList());

        List<Class> classList = Lists.newArrayList();
        resources.stream().filter(resource -> resource.isReadable()).forEach(uncheckedConsumer(readerFactory, typeFilter, classList));
        return classList;
    }

    @SneakyThrows
    private static Consumer<Resource> uncheckedConsumer(MetadataReaderFactory readerFactory, AnnotationTypeFilter typeFilter, List<Class> classList) {
        return resource -> {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            String className = reader.getClassMetadata().getClassName();
            if (typeFilter.match(reader, readerFactory)) {
                classList.add(Class.forName(className));
            }
        };
    }
}