package com.qdc.demoeurekacounsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-provider1",fallback = FeignClientFallback.class)
public interface UserClient {
//使用注解FeignClient中的name属性设置服务提供者名对应处理的请求是provider的控制层

    @RequestMapping(value = "/port")
    String hello();

    @RequestMapping(value = "/user/sayHi")
    String hello(@RequestParam(value = "sleep_seconds") int sleep_seconds);
}
