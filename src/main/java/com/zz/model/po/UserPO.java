package com.zz.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称:     hdzl
 * 类名称:       UserPO
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 14:20
 * 版本:         1.0
 */
@Data
@Builder
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    /**
     * 用户id
     */

    @TableId("userId")
    int userId;

    /**
     * 账户
     */
    @TableField("userName")
    String userName;

    /**
     * 密码
     */
    @TableField("passWord")
    String passWord;
}
