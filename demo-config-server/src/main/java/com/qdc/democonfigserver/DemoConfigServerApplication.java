package com.qdc.democonfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DemoConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConfigServerApplication.class, args);
        System.out.println("github上传测试");
        System.out.println("github上传测试");
        System.out.println("github上传测试");
        System.out.println("github上传测试");

    }

}
