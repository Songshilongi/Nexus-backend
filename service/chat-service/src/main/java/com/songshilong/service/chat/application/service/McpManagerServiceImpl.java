package com.songshilong.service.chat.application.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.enums.AgentExceptionEnum;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.domain.mcp.dao.entity.McpResourceEntity;
import com.songshilong.service.chat.domain.mcp.dao.mapper.McpResourceMapper;
import com.songshilong.service.chat.domain.mcp.req.McpResourceRequest;
import com.songshilong.service.chat.domain.mcp.req.NewMcpResourceRequest;
import com.songshilong.service.chat.domain.mcp.res.McpResourceResponse;
import com.songshilong.service.chat.interfaces.service.mcp.McpManagerService;
import com.songshilong.starter.database.base.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.service
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  16:21
 * @Description: McpServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class McpManagerServiceImpl implements McpManagerService {

    private final McpResourceMapper mcpResourceMapper;

    @Override
    public PageResult<McpResourceResponse> queryByUserId(Long userId, McpResourceRequest request) {
        IPage<McpResourceEntity> page = new Page<>(request.getPageNumber(), request.getPageSize());

        LambdaQueryWrapper<McpResourceEntity> queryWrapper = Wrappers.lambdaQuery(McpResourceEntity.class).eq(McpResourceEntity::getUserId, userId);
        IPage<McpResourceEntity> pageRes = this.mcpResourceMapper.selectPage(page, queryWrapper);
        List<McpResourceEntity> records = pageRes.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return null;
        }
        List<McpResourceResponse> list = records.stream()
                .map(McpResourceResponse::fromEntity)
                .toList();
        return PageResult.<McpResourceResponse>builder()
                .pageNumber(pageRes.getCurrent())
                .pages(pageRes.getPages())
                .pageSize(pageRes.getSize())
                .total(page.getTotal())
                .result(list)
                .build();
    }

    @Override
    public Boolean addMcpResource(Long userId, NewMcpResourceRequest request) {
        McpResourceEntity entity = BeanUtil.convert(request, McpResourceEntity.class);
        entity.setUserId(userId);
        boolean success = false;
        try {
            success = this.mcpResourceMapper.insert(entity) > 0;
        } catch (DuplicateKeyException e) {
            throw new BusinessException(AgentExceptionEnum.MCP_RESOURCE_EXISTED);
        }
        if (!success) {
            throw new BusinessException(AgentExceptionEnum.MCP_RESOURCE_ADD_FAILED);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateMcpResource(Long userId, NewMcpResourceRequest request) {
        LambdaUpdateWrapper<McpResourceEntity> updateWrapper = Wrappers.lambdaUpdate(McpResourceEntity.class)
                .set(McpResourceEntity::getEndpoint, request.getEndpoint())
                .set(McpResourceEntity::getNote, request.getNote())
                .eq(McpResourceEntity::getUserId, userId)
                .eq(McpResourceEntity::getResourceName, request.getResourceName());
        int update = this.mcpResourceMapper.update(updateWrapper);
        if (update != 1) {
            throw new BusinessException(AgentExceptionEnum.MCP_RESOURCE_UPDATE_FAILED);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteMcpResource(Long userId, String resourceName) {
        LambdaUpdateWrapper<McpResourceEntity> updateWrapper = Wrappers.lambdaUpdate(McpResourceEntity.class)
                .set(McpResourceEntity::getDeleted, 1)
                .eq(McpResourceEntity::getUserId, userId)
                .eq(McpResourceEntity::getResourceName, resourceName);

        int update = this.mcpResourceMapper.update(updateWrapper);
        if (update != 1) {
            throw new BusinessException(AgentExceptionEnum.MCP_RESOURCE_DELETE_FAILED);
        }
        return Boolean.TRUE;
    }


}
