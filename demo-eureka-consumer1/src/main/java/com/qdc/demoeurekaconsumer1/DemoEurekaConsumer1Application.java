package com.qdc.demoeurekaconsumer1;

import com.qdc.demoeurekaconsumer1.utils.HttpClientWithBasicAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.util.HashMap;
import java.util.Map;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class DemoEurekaConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaConsumer1Application.class, args);
//        HttpClientWithBasicAuth auth = new HttpClientWithBasicAuth();
//        String url = "http://localhost:8081/oauth/token";
//        Map<String,String> formData = new HashMap<String,String>();
//        formData.put("grant_type","client_credentials");
//        formData.put("scope","all");
//        System.out.println("formData:"+formData);
//        String result = auth.send(url,"test","123456",formData);
//        System.out.println("result:"+result);

    }

}
