package com.hdzl.service;

import com.hdzl.model.dto.UserDTO;
import com.hdzl.result.Result;

/**
 * 项目名称:     hdzl
 * 类名称:       UserService
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 14:12
 * 版本:         1.0
 */
public interface UserService {

    Result login(UserDTO userDTO);

    Result selctUserList();
}
