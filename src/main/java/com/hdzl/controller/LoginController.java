package com.hdzl.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hdzl.model.UserDTO;
import com.hdzl.result.Result;
import com.hdzl.result.ResultUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:     hdzl
 * 类名称:       Login
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/7 11:25
 * 版本:         1.0
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @RequestMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        return ResultUtil.success();
    }

    public static void main(String[] args) {
        String token= JWT.create().withAudience("123")// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256("123"));// 以 password 作为 token 的密钥
        System.out.println(token);
    }
}
