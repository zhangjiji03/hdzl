package com.aiboatapi.service.impl;

import com.aiboatapi.constant.RedisKey;
import com.aiboatapi.mapper.UserMapper;
import com.aiboatapi.model.dto.UserDTO;
import com.aiboatapi.model.po.UserPO;
import com.aiboatapi.result.Result;
import com.aiboatapi.result.ResultUtil;
import com.aiboatapi.service.UserService;
import com.aiboatapi.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称:     hdzl
 * 类名称:       UserServiceImpl
 * 类描述:       java类作用描述
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/8 14:12
 * 版本:         1.0
 */
@Slf4j
@Service
public class UserServiceImpl  implements UserService {


    @Autowired
    StringRedisTemplate redis;

    @Resource
    UserMapper userMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public Result login(UserDTO userDTO) {
        UserPO userPO=UserPO.builder().build();
        BeanUtils.copyProperties(userDTO,userPO );
        userPO=userMapper.selectOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUserName, userPO.getUserName()).eq(UserPO::getPassWord,userPO.getPassWord()));
        if(userPO!=null){
            String token= jwtTokenUtil.generateToken(userDTO.getUserName());
            redis.opsForValue().set(RedisKey.USER_TKOEN_KEY.concat(token),"123", 30000, TimeUnit.SECONDS);
            return ResultUtil.success(token);
        }else{
            return ResultUtil.error("用户不存在");
        }
    }

}
