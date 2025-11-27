package com.songshilong.starter.redis.core;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.redis.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:59
 * @Description: Cache
 * @Version: 1.0
 */
public interface Cache {

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
