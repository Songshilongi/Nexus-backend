package com.songshilong.service.chat;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import com.songshilong.module.starter.common.utils.oss.AliYunOssProperty;
import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.starter.database.util.MongoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  15:28
 * @Description: TestConnection
 * @Version: 1.0
 */
@SpringBootTest
public class TestConnection {
    @Autowired
    SnowflakeGenerator snowflakeGenerator;

    @Autowired
    MongoUtil mongoUtil;


    @Autowired
    AliYunOssProperty aliYunOssProperty;

    @Test
    public void gen() {
        long id = snowflakeGenerator.next();
        System.out.println("Generated ID: " + id);
    }

    @Test
    public void testOss() {
        System.out.println(aliYunOssProperty);
    }


    @Test
    public void testInsertMongo() {
        List<Message> messages = List.of(
                Message.ofUser("Hello, how are you?"),
                Message.ofAssistant("I'm fine, thank you! How can I assist you today?")
        );
        Long chatId = snowflakeGenerator.next();
        ConversationRecord record = ConversationRecord.builder()
                .id(chatId)
                .userId(1L)
                .messages(messages)
                .lastMessageTimestamp(System.currentTimeMillis())
                .deleted(0)
                .build();
        mongoUtil.getInstance().insert(record);
    }

    @Test
    public void queryMongo() {
        Long userId = 999L;
        System.out.println(mongoUtil.getInstance().find(
                        Query.query(Criteria.where(ConversationRecord.USER_ID).is(userId)), ConversationRecord.class
                )
                .stream()
                .map(ConversationRecord::getId)
                .collect(Collectors.toList()));
    }

    @Test
    public void query() {
        Long userId = 999L;
        System.out.println(mongoUtil.getInstance().find(
                        Query.query(
                                Criteria.where("user_id").is(userId)
                                        .and("_id").is(1996200262057857024L)
                        ),
                        ConversationRecord.class
                )
        );
    }

    @Test
    public void update() {
        Criteria criteria = Criteria.where("_id").is(1996200262057857024L);
        Query query = Query.query(criteria);
        // 4. **构建更新操作 (Update)**
        Update update = new Update();

        Message aNew = Message.ofUser("new");

        // 使用 $push 操作符将新消息添加到 messages 数组的末尾
        update.push("messages", aNew);

        // 同时更新 lastMessageTimestamp 字段
        update.set("lastMessageTimestamp", System.currentTimeMillis());

        // 5. **执行更新**
        // 使用 updateFirst() 确保只更新找到的第一个匹配文档
        UpdateResult result = mongoUtil.getInstance().updateFirst(query, update, ConversationRecord.class);
        System.out.println(result);

    }


}
