package com.xhyan.zero.cloud.account.utils;

import org.assertj.core.util.Lists;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

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
    public static List<Class> scanPackages(String packageName, Class<? extends Annotation> annotation) {
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(packageName) + RESOURCE_PATTERN;

        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
        List<Class> classList = Lists.newArrayList();
        AnnotationTypeFilter typeFilter = new AnnotationTypeFilter(annotation, false);

        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources(pattern);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
