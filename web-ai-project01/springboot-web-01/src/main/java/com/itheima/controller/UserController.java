package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.pojo.User;
import com.itheima.service.Impl.UserServiceImpl;
import com.itheima.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    /*@Qualifier("userServiceImpl2")
    @Autowired*/

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {

        //返回数据(json)
        return userService.findAll();
    }
}

