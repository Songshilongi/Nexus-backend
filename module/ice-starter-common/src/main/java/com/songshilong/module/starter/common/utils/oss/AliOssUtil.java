package com.songshilong.module.starter.common.utils.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import lombok.RequiredArgsConstructor;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.utils
 * @Author: Ice, Song
 * @CreateTime: 2025-12-04  17:22
 * @Description: AliOssUtil
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class AliOssUtil {

    private final AliYunOssProperty aliYunOssProperty;

    /**
     * 构建阿里云OSS客户端
     *
     * @return {@link OSS} 阿里云客户端
     */
    private OSS buildClient() {
        DefaultCredentialProvider defaultCredentialProvider =
                CredentialsProviderFactory.newDefaultCredentialProvider(aliYunOssProperty.getKeyId(),
                        aliYunOssProperty.getKeySecret());

        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);

        return OSSClientBuilder.create()
                .endpoint(aliYunOssProperty.getEndpoint())
                .credentialsProvider(defaultCredentialProvider)
                .region(aliYunOssProperty.getRegion())
                .build();
    }
}
