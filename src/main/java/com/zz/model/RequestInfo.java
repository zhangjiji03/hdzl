package com.zz.model;

import lombok.Data;

/**
 * 项目名称:     AiBoatApi
 * 类名称:       RequestInfo
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/6/19 21:57
 * 版本:         1.0
 */
@Data
public class RequestInfo {

    private String ip;

    private String url;

    private String httpMethod;

    private String classMethod;

    private Object requestParams;

    private Object result;

    private Long timeCost;

}
