package com.zz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 项目名称:     hdzl
 * 类名称:       PageController
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/2/7 14:47
 * 版本:         1.0
 */
@Controller
public class PageController {


    /**
     * 系统主页
     * @return
     */
    @GetMapping({ "/index", "/index.html"})
    public String index() {
        return "index";
    }


    /**
     * 系统登陆页
     * @return
     */
    @GetMapping({"", "/", "/login", "/login.html"})
    public String login() {
        return "login";
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping({ "/user", "/user.html"})
    public String user() {
        return "user";
    }

}
