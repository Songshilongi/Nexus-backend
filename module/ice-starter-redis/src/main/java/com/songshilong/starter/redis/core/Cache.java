package com.songshilong.starter.redis.core;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.redis.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:59
 * @Description: Cache
 * @Version: 1.0
 */
public interface Cache {

    String get(String key);


    Boolean delete(String key);

    /**
     * 获取缓存对象 - String
     *
     * @param key   redis key
     * @param clazz 缓存对象类型
     * @return T
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 设置缓存 - String
     *
     * @param key   key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 设置缓存 - String
     *
     * @param key      key
     * @param value    value
     * @param timeout  过期时间
     * @param timeUnit 过期时间的单位
     */
    void set(String key, String value, long timeout, TimeUnit timeUnit);

    /**
     * 判断Set中是否存在元素
     *
     * @param key key
     * @param obj 被判断的元素
     */
    Boolean setIsMember(String key, Object obj);

    /**
     * 向Set中添加元素
     *
     * @param key   key
     * @param value value
     */
    void setAdd(String key, String... value);
}
