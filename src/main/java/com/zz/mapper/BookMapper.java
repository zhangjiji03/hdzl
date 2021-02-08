package com.zz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zz.model.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目名称:     hdzl
 * 类名称:       cc
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/2/5 17:27
 * 版本:         1.0
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
