package com.songshilong.service.chat.infrastructure.mcp.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songshilong.module.starter.common.exception.ServiceException;
import com.songshilong.module.starter.common.exception.enums.AgentExceptionEnum;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.infrastructure.mcp.core.SchemaHelper;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.model.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.agent.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-09  15:57
 * @Description: AgentToolRegister
 * @Version: 1.0
 */
@Component
@Slf4j
public class LocalToolRegistry implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationContext applicationContext;
    private final ObjectMapper objectMapper;
    /**
     * 本地工具执行器映射容器
     */
    private final Map<String, ToolExecutor> localExecutors = new ConcurrentHashMap<>();

    public LocalToolRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.objectMapper = BeanUtil.getMapper();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            Class<?> targetClass = AopUtils.getTargetClass(bean);

            ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                if (method.isAnnotationPresent(AgentTool.class)) {
                    this.registerMethod(bean, method);
                }
            });
        }
    }

    private void registerMethod(Object bean, Method method) {
        AgentTool anno = method.getAnnotation(AgentTool.class);
        String name = anno.name().isEmpty() ? method.getName() : anno.name();

        McpTool definition = new McpTool(name, anno.description(), SchemaHelper.generateSchema(method));

        localExecutors.put(name, new LocalMethodExecutor(bean, method, definition, objectMapper));
        log.info("Loaded Local Tool: {}", name);
    }

    public Map<String, ToolExecutor> getTools() {
        return localExecutors;
    }

    private record LocalMethodExecutor(Object bean, Method method, McpTool definition,
                                       ObjectMapper mapper) implements ToolExecutor {
        @Override
        public McpTool getDefinition() {
            return definition;
        }

        @Override
        public Object execute(Map<String, Object> args) {
            try {
                Parameter[] methodParams = method.getParameters();
                Object[] invokeArgs = new Object[methodParams.length];

                String[] paramNames = SchemaHelper.PND.getParameterNames(method);

                if (paramNames == null) {
                    paramNames = new String[methodParams.length];
                    for (int i = 0; i < methodParams.length; i++) {
                        paramNames[i] = methodParams[i].getName();
                    }
                }

                for (int i = 0; i < methodParams.length; i++) {
                    String paramName = paramNames[i];
                    Class<?> paramType = methodParams[i].getType();

                    if (args != null && args.containsKey(paramName)) {
                        Object rawValue = args.get(paramName);

                        invokeArgs[i] = mapper.convertValue(rawValue, paramType);
                    } else {
                        invokeArgs[i] = null;
                    }
                }

                return method.invoke(bean, invokeArgs);

            } catch (Exception e) {
                throw new ServiceException(AgentExceptionEnum.LOCAL_TOOL_EXECUTE_FAIL);
            }
        }
    }
}
