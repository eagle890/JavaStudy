package com.qdc.demoeurekaprovider1.service;

import com.qdc.demoeurekaprovider1.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    public User selectUserById(String id){
        return new User("aaa","123","zip");

    }
    public List<User> selectAllUser(){
        ArrayList<User> users = new ArrayList<>();

        User user1 = new User("test001", "111", "aaa");
        User user2 = new User("test002", "111", "aaa");
        User user3 = new User("test003", "111", "aaa");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

    public boolean addUser(User u){
        return true;
    }
    public boolean updateUser(User u){
        return true;
    }
    public boolean deleteUser(String name){
        return true;
    }




}
