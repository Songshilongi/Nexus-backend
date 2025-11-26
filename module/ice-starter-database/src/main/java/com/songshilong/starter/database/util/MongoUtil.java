package com.songshilong.starter.database.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.database.util
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:55
 * @Description: MongoUtil
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class MongoUtil {

    private final MongoOperations mongoOperations;


    /**
     * 获取 MongoDb原始操作类
     */
    public MongoOperations getInstance() {
        return this.mongoOperations;
    }


    public <T> T findByDataId(String dataId, Class<T> clazz) {
        return mongoOperations.findById(dataId, clazz);
    }



}
