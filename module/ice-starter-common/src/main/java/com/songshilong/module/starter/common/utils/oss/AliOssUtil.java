package com.songshilong.module.starter.common.utils.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Objects;

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


    private static final String HTTPS = "https://";

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

    /**
     * 上传单个文件
     *
     * @param inputStream 文件资源
     * @param path        目标完整路径 /xx/yy/resource.xxx
     * @return 完整的请求路径
     */
    public String uploadFile(InputStream inputStream, String path) {
        OSS ossClient = null;
        String url = null;
        try {
            ossClient = this.buildClient();
            ossClient.putObject(aliYunOssProperty.getBucketName(), path, inputStream);
            url = HTTPS + aliYunOssProperty.getBucketName() + "." + aliYunOssProperty.getEndpoint() + "/" + path;
        } catch (OSSException | ClientException e) {
            throw new RuntimeException("文件上传失败");
        } finally {
            if (!Objects.isNull(ossClient)) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}
