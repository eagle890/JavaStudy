package com.qdc.demoeurekaauth_server;

import com.alibaba.druid.pool.DruidDataSource;

import com.qdc.demoeurekaauth_server.mapper.UserMapper;
import com.qdc.demoeurekaauth_server.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoEurekaAuthServerApplicationTests {

    @Autowired
    private DruidDataSource druidDataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void UserDetailsTest(){
        System.out.println(userDetailsService.loadUserByUsername("admin"));
    }

    @Test
    public void contestLoads(){
        System.out.println(druidDataSource);
    }
    @Test
    public void userTest(){
        System.out.println(userMapper.findByUsername("a"));
    }

    @Test
    public void userIdTest(){
        System.out.println(userMapper.findById(1));
    }



}
