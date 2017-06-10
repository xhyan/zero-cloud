package com.xhyan.zero.cloud.account.converter.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.codegen.MappingCodeGenerator;
import com.baidu.unbiz.easymapper.exception.MappingException;
import com.baidu.unbiz.easymapper.metadata.ClassMap;
import com.baidu.unbiz.easymapper.metadata.MapperKey;
import com.baidu.unbiz.easymapper.metadata.TypeFactory;
import com.baidu.unbiz.easymapper.util.Memoizer;
import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zero 转换器映射,单例
 *
 * @author xhyan
 */
public enum ZeroConvertMapper implements Mapper {

    MAPPER;

    private static final Logger LOGGER = LoggerFactory.getLogger(ZeroConvertMapper.class);

    /**
     * 类型映射关系缓存
     */
    private Memoizer<MapperKey, ClassMap<Object, Object>> classMapCache = new Memoizer();

    /**
     * 已经经过映射注册{@link ClassMapBuilder#register()}的Mapper缓存
     */
    private Memoizer<MapperKey, AtoBMapping<Object, Object>> mapperCache = new Memoizer();

    /**
     * 源代码生成器
     */
    private MappingCodeGenerator codeGenerator = new MappingCodeGenerator();

    /**
     * 清理
     */
    public void clear() {
        if (this.classMapCache != null) {
            this.classMapCache.clear();
        }
        if (this.mapperCache != null) {
            this.mapperCache.clear();
        }
    }

    /**
     * 注册类型映射关系
     *
     * @param key     映射的key
     * @param builder 映射关系构造器
     * @param <A>     源类型
     * @param <B>     目标类型
     * @return 类型映射关系
     */
    @Override
    @SneakyThrows(InterruptedException.class)
    public <A, B> ClassMap<A, B> registerClassMap(MapperKey key, final ClassMapBuilder<A, B> builder) {
            return (ClassMap<A, B>) classMapCache.compute(key, arg -> (ClassMap<Object, Object>) builder.build());

    }

    /**
     * 从源类型到目标类型做一个映射
     *
     * @param aType 源类型
     * @param bType 目标类型
     * @param <A>   源类型class
     * @param <B>   目标类型class
     * @return 类型映射关系构造器
     */
    @Override
    public <A, B> ClassMapBuilder<A, B> mapClass(Class<A> aType, Class<B> bType) {
        return new ClassMapBuilder<>(TypeFactory.valueOf(aType), TypeFactory.valueOf(bType), this);
    }

    /**
     * 执行mapping操作
     *
     * @param sourceObject 源对象
     * @param targetClass  目标类型
     * @param <A>          源类型
     * @param <B>          目标类型
     * @return 目标对象
     * @throws MappingException
     */
    @Override
    public <A, B> B map(A sourceObject, Class<B> targetClass) throws MappingException {
        return map(sourceObject, null, targetClass);
    }

    /**
     * 执行mapping操作
     *
     * @param sourceObject 源对象
     * @param targetObject 目标对象
     * @param <A>          源类型
     * @param <B>          目标类型
     * @return 目标对象
     * @throws MappingException
     */
    @Override
    public <A, B> B map(A sourceObject, B targetObject) throws MappingException {
        return map(sourceObject, targetObject, (Class<B>) targetObject.getClass());
    }

    /**
     * 执行mapping操作
     *
     * @param sourceObject 源对象
     * @param b            目标对象，可为空，为空则会构造一个
     * @param targetClass  目标类型
     * @param <A>          源类型
     * @param <B>          目标类型
     * @return 目标对象
     */
    private <A, B> B map(A sourceObject, B b, Class<B> targetClass) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Mapping {} to {}", TypeFactory.valueOf(sourceObject.getClass()),
                        TypeFactory.valueOf(targetClass));
            }
            MapperKey key = new MapperKey(TypeFactory.valueOf(sourceObject.getClass()),
                    TypeFactory.valueOf(targetClass));
            final ClassMap<A, B> classMap = (ClassMap<A, B>) classMapCache.get(key);
            if (classMap == null) {
                throw new MappingException(
                        "No class map found for " + key + ", make sure type or nested type is registered beforehand");
            }
            AtoBMapping<A, B> mapper =
                    (AtoBMapping<A, B>) mapperCache.compute(key,
                            arg -> codeGenerator.build(classMap, self()));
            if (mapper == MappingCodeGenerator.ABSENT_MAPPING) {
                throw new MappingException("Generating mapping code failed for " + classMap + ", this should not "
                        + "happen, probably the framework could not handle mapping correctly based on your bean");
            }
            if (b == null) {
                b = ReflectionUtil.newInstance(targetClass);
            }
            mapper.map(sourceObject, b);
            return b;
        } catch (InterruptedException e) {
            throw new MappingException("Memoizer has been interrupted", e);
        } catch (MappingException e) {
            throw e;
        } catch (Exception e) {
            throw new MappingException(e);
        }
    }

    /**
     * mapping自己的引用
     *
     * @return Mapper
     */
    private Mapper self() {
        return this;
    }

}

