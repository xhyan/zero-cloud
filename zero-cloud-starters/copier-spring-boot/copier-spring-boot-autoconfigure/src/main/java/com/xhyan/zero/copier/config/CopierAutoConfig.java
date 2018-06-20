package com.xhyan.zero.copier.config;

import com.xhyan.zero.copier.annotations.Copier;
import com.xhyan.zero.copier.annotations.CopierConverter;
import com.xhyan.zero.copier.annotations.CopierField;
import com.xhyan.zero.copier.annotations.EmptyMapper;
import com.xhyan.zero.copier.utils.AnnotationUtil;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.FieldMapBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 复制器的自动配置类
 *
 * @author yanliwei
 */
@Configuration
@ConditionalOnClass(value = MapperFactory.class)
@EnableConfigurationProperties(CopierProperties.class)
public class CopierAutoConfig{

    @Autowired
    private CopierProperties copierProperties;

    @Bean
    ConfigurableMapper mapperFacade(){
        return new ConfigurableMapper(){
            @Override
            protected void configure(MapperFactory factory) {
                registerConverter(factory);
                registerCopier(factory);
            }

        };
    }

    /**
     * 复制器注册，处理类和类字段级别映射关系注册
     */
    private void registerCopier(MapperFactory factory) {
        String copierPackages = copierProperties.getCopierPackages();
        if (StringUtils.isBlank(copierPackages)) {
            return;
        }
        Stream.of(copierPackages.split(",")).forEach(path -> {
            List<Class> classes = AnnotationUtil.scanPackages(Copier.class,
                path);
            classes.forEach(clazz -> {
                Copier copier = (Copier) clazz.getDeclaredAnnotation(Copier.class);
                ClassMapBuilder classMap = factory.classMap(clazz, copier.mapClass());
                //判断是否自定义对象映射器
                if (!copier.mapper().isAssignableFrom(EmptyMapper.class)) {
                    //优先走自定义映射器
                    try {
                        classMap.customize(copier.mapper().newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    //获得所有属性
                    Field[] declaredFields = clazz.getDeclaredFields();
                    Stream.of(declaredFields).forEach(field -> {
                        if (field.isAnnotationPresent(CopierField.class)) {
                            CopierField copierField = field.getAnnotation(CopierField.class);
                            FieldMapBuilder fieldMap = classMap
                                .fieldMap(field.getName(), copierField.mapFiled()).direction(copierField.direction())
                                .mapNulls(copierField.mapNulls()).mapNullsInReverse(copierField.mapNullsInReverse());
                            //自定义属性转换器
                            if (StringUtils.isNotBlank(copierField.converter())) {
                                fieldMap.converter(copierField.converter());
                            }
                            //是否忽略该属性
                            if (copierField.isIgnore()) {
                                fieldMap.exclude();
                            }
                            fieldMap.add();

                        }
                    });
                }
                //其余走默认策略
                classMap.byDefault().register();
            });
        });
    }

    /**
     * 转换器注册
     */
    private void registerConverter(MapperFactory factory) {
        String convertPackages = copierProperties.getConvertPackages();
        if (StringUtils.isBlank(convertPackages)) {
            return;
        }
        ConverterFactory converterFactory = factory.getConverterFactory();
        Stream.of(convertPackages.split(",")).forEach(path -> {
            List<Class> classes = AnnotationUtil.scanPackages(CopierConverter.class,
                path);
            classes.forEach(clazz -> {
                //存在CopierConverter注解并且是Converter的子类
                if (clazz.isAnnotationPresent(CopierConverter.class) && Converter.class
                    .isAssignableFrom(clazz)) {
                    CopierConverter converter = (CopierConverter) clazz
                        .getDeclaredAnnotation(CopierConverter.class);
                    try {
                        Converter o = (Converter) clazz.newInstance();
                        converterFactory.registerConverter(converter.name(), o);
                    } catch (InstantiationException e) {

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            });
        });
    }


    public static void main(String[] args) {
        boolean assignableFrom = EmptyMapper.class.isAssignableFrom(EmptyMapper.class);
        System.out.println(assignableFrom);
    }
}
