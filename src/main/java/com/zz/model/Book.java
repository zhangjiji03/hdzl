package com.zz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称:     hdzl
 * 类名称:       classId
 * 类描述:       java类作用描述
 * 创建人:       zhangzhe@biconjs.com
 * 创建时间:     2021/2/5 17:23
 * 版本:         1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Book")
public class Book {

    @TableId("id")
    int id;

    @TableField("name")
    String name;


}
