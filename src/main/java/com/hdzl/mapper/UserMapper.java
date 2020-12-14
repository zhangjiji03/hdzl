package com.hdzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hdzl.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目名称:     hdzl
 * 类名称:       UserMapper
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 14:14
 * 版本:         1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
