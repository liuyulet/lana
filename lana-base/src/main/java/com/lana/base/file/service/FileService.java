package com.lana.base.file.service;

import java.io.InputStream;

/**
 * 存储服务
 */
public interface FileService {


    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public String upload(InputStream inputStream, String path);

}
