
package com.example.login.controller;

import com.example.login.bean.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if (userService.login(user)) {
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}
