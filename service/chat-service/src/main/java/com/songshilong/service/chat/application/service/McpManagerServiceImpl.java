package com.songshilong.service.chat.application.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songshilong.service.chat.domain.mcp.dao.entity.McpResourceEntity;
import com.songshilong.service.chat.domain.mcp.dao.mapper.McpResourceMapper;
import com.songshilong.service.chat.domain.mcp.req.McpResourceRequest;
import com.songshilong.service.chat.domain.mcp.res.McpResourceResponse;
import com.songshilong.service.chat.interfaces.service.mcp.McpManagerService;
import com.songshilong.starter.database.base.PageResult;
import lombok.RequiredArgsConstructor;
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


}
