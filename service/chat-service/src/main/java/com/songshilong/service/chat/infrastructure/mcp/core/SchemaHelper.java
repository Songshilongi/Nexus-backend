package com.songshilong.service.chat.infrastructure.mcp.core;

import com.songshilong.service.chat.infrastructure.mcp.model.InputSchema;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  14:03
 * @Description: SchemaHelper - Java Object --> JSON Schema
 * @Version: 1.0
 */
public class SchemaHelper {

    public static final ParameterNameDiscoverer PND = new DefaultParameterNameDiscoverer();

    /**
     * 根据方法参数生成输入参数的 JSON Schema
     * 获取参数真实名称 (需要编译时开启 -parameters，或者通过 pnd 尽力获取)
     *
     * @param method 方法
     * @return {@link InputSchema}
     */
    public static InputSchema generateSchema(Method method) {
        Map<String, Object> properties = new HashMap<>();
        List<String> required = new ArrayList<>();

        String[] paramNames = PND.getParameterNames(method);
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            Parameter p = parameters[i];
            String name = (paramNames != null && paramNames.length > i) ? paramNames[i] : p.getName();
            String type = getJsonType(p.getType());
            properties.put(name, Map.of("type", type, "description", "Parameter: " + name));
            required.add(name);
        }

        return new InputSchema("object", properties, required);
    }

    /**
     * 获取 Java 类型对应的 JSON Schema 类型
     * @param clazz 类
     * @return JSON 类型
     */
    private static String getJsonType(Class<?> clazz) {
        if (Number.class.isAssignableFrom(clazz) || clazz.isPrimitive()) {
            if (clazz == boolean.class || clazz == Boolean.class) return "boolean";
            if (clazz == char.class || clazz == Character.class) return "string";
            if (clazz == void.class) return "null";
            if (clazz == int.class || clazz == Integer.class || clazz == long.class || clazz == Long.class)
                return "integer";
            return "number";
        }
        return "string";
    }
}
