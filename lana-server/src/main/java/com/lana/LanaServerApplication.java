package com.lana;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * todo 后续要将分页查询中的数据排序加上
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
//@ComponentScan("com.lana.*")
//@MapperScan("com.lana.*")
public class LanaServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        try {
            SpringApplication.run(LanaServerApplication.class, args);
            // 获取本地主机对象
            InetAddress localHost = InetAddress.getLocalHost();
            log.info("\n----------------------------------------------------------------------------\n\t" +
                    "Application Lana-Server is running!  :\n\t" +
                    "本地接口文档：http://{}:8888/lana/doc.html\n\t" +
                    "如果不能访问，请去IgnoreUrlsConfig文件中放开url拦截\n" +
                    "----------------------------------------------------------------------------",localHost.getHostAddress());
        }catch (UnknownHostException e) {
            // 处理无法获取主机信息的情况
            e.printStackTrace();
        }
    }


}
