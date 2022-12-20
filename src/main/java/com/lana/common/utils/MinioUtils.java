package com.lana.common.utils;

/**
 * @auther liuyulet
 * @date 2022/9/15 21:13
 */

import com.alibaba.fastjson.JSONObject;
import com.lana.config.MinioConfig;
import io.minio.*;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class MinioUtils {

    private static Logger log = LoggerFactory.getLogger(MinioUtils.class);

    private static MinioClient minioClient;

    private static String endpoint;
    private static String bucketname;
    private static String accesskey;
    private static String secretkey;
    private static String httppoint;

    private static final String SEPARATOR = "/";


    // 使用main测试路径 2021-11-08 优化帮助类
//    public static void main(String[] args) {
//        FileUploadMinIoUtils fileUploadMinIoUtils=new FileUploadMinIoUtils("http://10.211.55.16:9000","leida","leida1234","leida1234");
//        try {
//            FileUploadMinIoUtils.putObject("leida","fdaf.png","/Users/eric/Desktop/12.png");
//        }catch (Exception e){
//
//        }
//    }

    {createMinioClient();}

    /**
     * 创建minioClient
     */
    public static void createMinioClient() {
        endpoint = MinioConfig.getEndpoint();
        bucketname = MinioConfig.getBucketname();
        accesskey = MinioConfig.getAccesskey();
        secretkey = MinioConfig.getSecretkey();
        httppoint = MinioConfig.getSecretkey();

        try {
            if (null == minioClient) {
                log.info("url:"+endpoint+"用户名："+accesskey+"密码："+secretkey+"桶名："+bucketname);

                log.info("minioClient create start11111111111111111111111111111111111111111111111111");
                minioClient = MinioClient.builder().endpoint(endpoint).credentials(accesskey, secretkey)
                        .build();
                createBucket();
                log.info("minioClient create end111111111111111111111111111111111111111111111111111111111111");
            }
        } catch (Exception e) {
            log.error("连接MinIO服务器异常：{}", e);
        }
    }

    /**
     * 获取上传文件的基础路径
     *
     * @return url
     */
    public static String getBasisUrl() {
        return httppoint + SEPARATOR + bucketname + SEPARATOR;
    }


    /**
     * 初始化Bucket
     *操作存储桶
     * @throws Exception 异常
     */
    private static void createBucket()
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketname).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketname).build());
        }
    }

    /**
     * 验证bucketname是否存在
     *
     * @return boolean true:存在
     */
    public static boolean bucketExists()
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketname).build());
    }

    /**
     * 创建bucket
     *
     * @param bucketname bucket名称
     */
    public static void createBucket(String bucketname)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketname).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketname).build());
        }
    }


    /**
     * 获取存储桶策略
     *
     * @param bucketname 存储桶名称
     * @return json
     */
    private JSONObject getBucketPolicy(String bucketname)
            throws IOException, InvalidKeyException, InvalidResponseException, BucketPolicyTooLargeException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException {
        String bucketPolicy = minioClient
                .getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketname).build());
        return JSONObject.parseObject(bucketPolicy);
    }

    /**
     * 获取全部bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
     */
    public static List<Bucket> getAllBuckets()
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketname获取信息
     *
     * @param bucketname bucket名称
     */
    public static Optional<Bucket> getBucket(String bucketname)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketname)).findFirst();
    }

    /**
     * 根据bucketname删除信息
     *
     * @param bucketname bucket名称
     */
    public static void removeBucket(String bucketname)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketname).build());
    }



    /**
     * 判断文件是否存在
     *
     * @param bucketname 存储桶
     * @param objectName 对象
     * @return true：存在
     */
    public static boolean doesObjectExist(String bucketname, String objectName) {
        boolean exist = true;
        try {
            minioClient
                    .statObject(StatObjectArgs.builder().bucket(bucketname).object(objectName).build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketname 存储桶
     * @param objectName 文件夹名称（去掉/）
     * @return true：存在
     */
    public static boolean doesFolderExist(String bucketname, String objectName) {
        boolean exist = false;
        try {
            Iterable<io.minio.Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketname).prefix(objectName).recursive(false).build());
            for (io.minio.Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && objectName.equals(item.objectName())) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 根据文件前置查询文件
     *
     * @param bucketname bucket名称
     * @param prefix 前缀
     * @param recursive 是否递归查询
     * @return MinioItem 列表
     */
    public static List<Item> getAllObjectsByPrefix(String bucketname, String prefix,
                                                   boolean recursive)
            throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException,
            IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        List<Item> list = new ArrayList<>();
        Iterable<io.minio.Result<Item>> objectsIterator = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketname).prefix(prefix).recursive(recursive).build());
        if (objectsIterator != null) {
            for (Result<Item> o : objectsIterator) {
                Item item = o.get();
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取文件流
     *
     * @param bucketname bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    public static InputStream getObject(String bucketname, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient
                .getObject(GetObjectArgs.builder().bucket(bucketname).object(objectName).build());
    }

    /**
     * 获取文件流
     *
     * @param objectName 文件名称
     * @return 二进制流
     */
    public static InputStream getObject( String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient
                .getObject(GetObjectArgs.builder().bucket(bucketname).object(objectName).build());
    }

    /**
     * 断点下载
     *
     * @param bucketname bucket名称
     * @param objectName 文件名称
     * @param offset 起始字节的位置
     * @param length 要读取的长度
     * @return 流
     */
    public InputStream getObject(String bucketname, String objectName, long offset, long length)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.getObject(
                GetObjectArgs.builder().bucket(bucketname).object(objectName).offset(offset).length(length)
                        .build());
    }



    /**
     * 通过MultipartFile，上传文件
     * @param file 文件
     * @param objectName 对象名
     */
    public static ObjectWriteResponse putObject( MultipartFile file,
                                                 String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        InputStream inputStream = file.getInputStream();
        return minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketname).object(objectName).contentType(file.getContentType())
                        .stream(
                                inputStream, inputStream.available(), -1)
                        .build());
    }

    /**
     * 通过MultipartFile，上传文件
     *
     * @param bucketname 存储桶
     * @param file 文件
     * @param objectName 对象名
     * @param contentType  如 image/png
     */
    public static ObjectWriteResponse putObject(String bucketname, MultipartFile file,
                                                String objectName, String contentType)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        InputStream inputStream = file.getInputStream();

        return minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketname).object(objectName).contentType(contentType)
                        .stream(
                                inputStream, inputStream.available(), -1)
                        .build());
    }

    /**
     * 上传本地文件
     *
     * @param bucketname 存储桶
     * @param objectName 对象名称
     * @param fileName 本地文件路径
     */
    public static ObjectWriteResponse putObject(String bucketname, String objectName,
                                                String fileName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketname).object(objectName).filename(fileName).build());
    }

    /**
     * 上传本地文件
     *
     * @param
     * @param objectName 对象名称
     * @param fileName 本地文件路径
     */
    public static ObjectWriteResponse putObject(String objectName,
                                                String fileName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketname).object(objectName).filename(fileName).build());
    }

    /**
     * 通过流上传文件
     *
     * @param bucketname 存储桶
     * @param objectName 文件对象
     * @param inputStream 文件流
     */
    public static ObjectWriteResponse putObject(String bucketname, String objectName,
                                                InputStream inputStream)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketname).object(objectName).stream(
                        inputStream, inputStream.available(), -1)
                        .build());
    }

    /**
     * 创建文件夹或目录
     *
     * @param bucketname 存储桶
     * @param objectName 目录路径
     */
    public static ObjectWriteResponse putDirObject(String bucketname, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketname).object(objectName).stream(
                        new ByteArrayInputStream(new byte[]{}), 0, -1)
                        .build());
    }

    /**
     * 获取文件信息, 如果抛出异常则说明文件不存在
     *
     * @param bucketname bucket名称
     * @param objectName 文件名称
     */
    public static StatObjectResponse statObject(String bucketname, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient
                .statObject(StatObjectArgs.builder().bucket(bucketname).object(objectName).build());
    }

    /**
     * 拷贝文件
     *
     * @param bucketname bucket名称
     * @param objectName 文件名称
     * @param srcbucketname 目标bucket名称
     * @param srcObjectName 目标文件名称
     */
    public static ObjectWriteResponse copyObject(String bucketname, String objectName,
                                                 String srcbucketname, String srcObjectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.copyObject(
                CopyObjectArgs.builder()
                        .source(CopySource.builder().bucket(bucketname).object(objectName).build())
                        .bucket(srcbucketname)
                        .object(srcObjectName)
                        .build());
    }

    /**
     * 删除文件
     *
     * @param bucketname bucket名称
     * @param objectName 文件名称
     */
    public static void removeObject(String bucketname, String objectName)
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        minioClient
                .removeObject(RemoveObjectArgs.builder().bucket(bucketname).object(objectName).build());
    }

    /**
     * 批量删除文件
     *
     * @param bucketname bucket
     * @param keys 需要删除的文件列表
     * @return
     */
    public static void removeObjects(String bucketname, List<String> keys) {
        List<DeleteObject> objects = new LinkedList<>();
        keys.forEach(s -> {
            objects.add(new DeleteObject(s));
            try {
                removeObject(bucketname, s);
            } catch (Exception e) {
                log.error("批量删除失败！error:{}",e);
            }
        });
    }

    /**
     * 将URLDecoder编码转成UTF8
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getUtf8ByURLDecoder(String str) throws UnsupportedEncodingException {
        String url = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        return URLDecoder.decode(url, "UTF-8");

    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static String getBucketname() {
        return bucketname;
    }

    public static String getAccesskey() {
        return accesskey;
    }

    public static String getSecretkey() {
        return secretkey;
    }

    public static String getHttppoint() {
        return httppoint;
    }
}

