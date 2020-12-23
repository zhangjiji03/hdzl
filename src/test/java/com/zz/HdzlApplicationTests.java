package com.zz;

import com.zz.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class zzApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void test(){

        String ss=jwtTokenUtil.generateToken("zz");
        System.out.println(ss);

        System.out.println(jwtTokenUtil.refreshToken(ss));
    }


}
