package com.zz.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.zz.result.Result;
import com.zz.result.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:     AiBoatApi
 * 类名称:       LoginController
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/6/19 21:51
 * 版本:         1.0
 */
@RequestMapping("/logger")
@RestController
public class LoggerController {

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @RequestMapping("setLogger")
    public Result setLogger(int i) throws Exception{
        if (i==0) {
            loggerContext.getLogger("root").setLevel(Level.INFO);

        }else{
            loggerContext.getLogger("root").setLevel(Level.ERROR);
        }
        return ResultUtil.success();
    }
}
