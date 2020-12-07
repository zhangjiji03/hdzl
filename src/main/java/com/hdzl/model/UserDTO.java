package com.hdzl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称:     hdzl
 * 类名称:       UserDTO
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/7 11:30
 * 版本:         1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * 账户
     */
    String userName;

    /**
     * 密码
     */
    String passWord;
}
