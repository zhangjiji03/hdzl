package com.aiboatapi.controller;

import com.aiboatapi.model.dto.UserDTO;
import com.aiboatapi.result.Result;
import com.aiboatapi.result.ResultUtil;
import com.aiboatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 项目名称:     hdzl
 * 类名称:       Login
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/7 11:25
 * 版本:         1.0
 */
@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody @Valid UserDTO userDTO){
        return userService.login(userDTO);

    }

    @RequestMapping("/selctUserList")
    public Result selctUserList(){
        return userService.selctUserList();
    }

    @RequestMapping("/test")
    public Result test(){
        return ResultUtil.success("测试成立");

    }


}
