package com.zz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zz.constant.RedisKey;
import com.zz.mapper.BookMapper;
import com.zz.mapper.UserMapper;
import com.zz.model.dto.UserDTO;
import com.zz.model.po.UserPO;
import com.zz.result.Result;
import com.zz.result.ResultUtil;
import com.zz.service.UserService;
import com.zz.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    BookMapper hhaha;

    @Value("${outTime}")
    public String outTime;

    @Override
    public Result login(UserDTO userDTO) {
        UserPO userPO=UserPO.builder().build();
        BeanUtils.copyProperties(userDTO,userPO );
        userPO=userMapper.selectOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUserName, userPO.getUserName()).eq(UserPO::getPassWord,userPO.getPassWord()));
        if(userPO!=null){
            String userId=String.valueOf(userPO.getUserId());
            String token=redis.opsForValue().get(RedisKey.USER_TKOEN_KEY.concat(userId));
            if(StringUtils.isNotBlank(token)){
                redis.delete(RedisKey.USER_TKOEN_KEY.concat(userId));
            }
            token= jwtTokenUtil.generateToken(userDTO.getUserName());
            redis.opsForValue().set(RedisKey.USER_TKOEN_KEY.concat(userId),token, Long.parseLong(outTime), TimeUnit.SECONDS);
            return ResultUtil.success(token);
        }else{
            return ResultUtil.error("账号密码错误");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result selctUserList(){
        return ResultUtil.success();
    }



}
