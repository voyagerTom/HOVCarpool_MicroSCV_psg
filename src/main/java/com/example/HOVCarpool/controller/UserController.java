package com.example.HOVCarpool.controller;


import com.example.HOVCarpool.dao.UserRepository;
import com.example.HOVCarpool.dto.UserDto;
import com.example.HOVCarpool.entity.User;
import com.example.HOVCarpool.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name="UserController",description = "給非會員使用")
public class UserController {

    //取得log 物件
    private static final Logger log =  LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Operation(summary = "讓使用者註冊為一般會員",description = "返回是否註冊成功",responses = {
            @ApiResponse(responseCode = "200", description = "回傳角色創建成功 & 角色資訊")
    })
    public ResponseEntity<String> register( @RequestBody User user ){
        User newUser=null;
        log.info("創建使用帳號 開始 欲申請的帳號: {}  ",user.getName());
        newUser = userRepository.findByEmail(user.getEmail());
        if (newUser!=null){
            log.info("角色創建失敗，帳號已存在。ACT:{}",user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("角色創建失敗，帳號已存在。ACT: "+user.getEmail());
        }

        newUser=userService.register(user);
        log.info("角色創建成功 ACT:{}",user.getEmail());
        return  ResponseEntity.status(HttpStatus.CREATED).body("角色創建成功" + newUser.toString());
    }

    @GetMapping("/search")
    public List<User> getUserByName(@RequestParam(required = false)String name) {
        List<User> user = userService.getUserByName(name);
        log.info("search ok");
        return user;
    }


}