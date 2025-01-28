package com.example.HOVCarpool.service;


import com.example.HOVCarpool.dao.UserRepository;
import com.example.HOVCarpool.dao.UserRoleMapRepository;
import com.example.HOVCarpool.dto.UserDto;
import com.example.HOVCarpool.entity.User;
import com.example.HOVCarpool.entity.UserRoleMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleMapRepository userRoleMapRepository;

    public List<User> getUserByName(String name) {
        List<User> userList = null;
        if (name == null) {
            userList = userRepository.findAll();
            return userList;
        }

        return userRepository.findByName(name);
    }


    public User register(User user) {
        //檢查是否已申請過 check by IDNO
        User userExist = null;
        userRepository.save(user);
        userExist = userRepository.findByEmail(user.getEmail());

        UserRoleMap  userRoleMap=new UserRoleMap();
        userRoleMap.setUserId(userExist.getId());
        userRoleMap.setRoleId(100);
        userRoleMapRepository.save(userRoleMap);
        return userExist;


    }

    public boolean isExist(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return true;
        }
        return false;
    }
}
