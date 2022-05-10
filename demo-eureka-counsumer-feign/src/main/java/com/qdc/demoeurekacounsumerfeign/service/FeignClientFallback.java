package com.qdc.demoeurekacounsumerfeign.service;

public class FeignClientFallback implements UserClient{
    @Override
    public String hello() {
        return "";
    }

    @Override
    public String hello(int sleep_seconds) {
        return "Hi,容错保护机制已经启动了！";
    }
}
