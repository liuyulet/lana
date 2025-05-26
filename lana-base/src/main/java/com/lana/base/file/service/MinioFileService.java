package com.lana.base.file.service;

import com.lana.base.file.config.MinioConfig;
import com.lana.base.syshandle.exception.LanaException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

/**
 * Minio存储
 */
@Slf4j
@Component
public class MinioFileService implements FileService {

    private final MinioConfig properties;
    private final MinioClient minioClient;

    public MinioFileService(MinioConfig properties, MinioClient minioClient) {
        this.properties = properties;
        this.minioClient = minioClient;
    }


    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            //如果BucketName不存在，则创建
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
            }
            String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(path);
            if (mediaType.isPresent()) {
                contentType = mediaType.get().toString();
            }
            minioClient.putObject(
                PutObjectArgs.builder().bucket(properties.getBucketName()).contentType(contentType).object(path).stream(inputStream, inputStream.available(), -1).build()
            );

        } catch (Exception e) {
            throw new LanaException("上传文件失败：", e);
        }
        return properties.getEndPoint() + "/" + properties.getBucketName() + "/" + path;
    }
}
