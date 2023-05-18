package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    //测试jwt令牌
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new LinkedCaseInsensitiveMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "wangnine") //签名算法
                .setClaims(claims)  //自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期为一个小时
                .compact();//返回一个字符串的令牌
        System.out.println(jwt);
    }


    //进行jwt令牌的解析
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("wangnine")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmFtZSI6InRvbSIsImV4cCI6MTY4NDE2MDE5N30._d1KY__Qxo8iHf9tlsLgxLgaBuDHMzy6ft_BeCUxm3c")
                .getBody();
        System.out.println(claims);
    }
}
