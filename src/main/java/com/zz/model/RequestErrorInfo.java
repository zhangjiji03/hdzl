package com.zz.model;

import lombok.Data;

/**
 * 项目名称:     AiBoatApi
 * 类名称:       RequestErrorInfo
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/6/19 21:56
 * 版本:         1.0
 */
@Data
public class RequestErrorInfo {

    private String ip;

    private String url;

    private String httpMethod;

    private String classMethod;

    private Object requestParams;

    private RuntimeException exception;

}
