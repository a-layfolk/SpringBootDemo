package com.example.JwtDemo2.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {

    private static final String SIGN = "ASHGAHA%^A*D*(A^SD*AD";


    /**
     * 返回 token
     * @param map 以键值对方式传入参数
     * @return
     */
    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.DATE, 7); // 设置为 7 天过期

        // 创建 jwt builder
        JWTCreator.Builder builder = JWT.create();

        // 放入 payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        String token = builder.withExpiresAt(instance.getTime())    // 指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));// 签名

        return token;
    }

    /**
     * 验证 token, 如果出错会直接报异常
     * @param token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获得 token 加密的信息,
     * 可以通过 verify.getClaim("userId").asString()
     * verify.getClaim("username").asString() 等获取值
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
}
