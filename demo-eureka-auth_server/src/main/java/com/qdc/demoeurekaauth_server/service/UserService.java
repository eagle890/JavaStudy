package com.qdc.demoeurekaauth_server.service;

import com.qdc.demoeurekaauth_server.pojo.User;

public interface UserService {

    User getUser(String username);
}
