package com.songshilong.service.chat.infrastructure.llm.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;


/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.message
 * @Author: Ice, Song
 * @CreateTime: 2025-12-05  13:37
 * @Description: Content
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    @Field("type")
    private String type;
    @Field("text")
    private String text;

    @Field("image_url")
    private Map<String, String> image_url;

    public static Content ofText(String text) {
        return new Content("text", text, null);
    }

    public static Content ofImage(String imageUrl) {
        return new Content("image_url", null, Map.of("url", imageUrl));
    }



}
