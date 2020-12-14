package com.hdzl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "账户id不能为空")
    String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    String passWord;
}
