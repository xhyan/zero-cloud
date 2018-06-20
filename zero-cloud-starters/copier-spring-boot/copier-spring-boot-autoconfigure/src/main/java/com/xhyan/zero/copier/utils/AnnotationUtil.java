package com.xhyan.zero.copier.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

/**
 * 注解工具类
 *
 * @author yanliwei
 */
public class AnnotationUtil {

    /**
     * 需要扫描的资源路径
     */
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    /**
     * 扫描package下面，并获取所有带有指定注解的类
     */
    public static List<Class> scanPackages(Class<? extends Annotation> annotation,
        String... packageName) {

        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
        AnnotationTypeFilter typeFilter = new AnnotationTypeFilter(annotation, false);
        List<Class> classList = new ArrayList<>();
        try {
            List<Resource> resources = new ArrayList<>();
            for (String pkgName : packageName) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(pkgName) + RESOURCE_PATTERN;
                resources.addAll(Arrays.asList(resolver.getResources(pattern)));
            }

            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    if (typeFilter.match(reader, readerFactory)) {
                        classList.add(Class.forName(className));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("consumerListenerPackages设置的包路径不合法");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classList;
    }
}
