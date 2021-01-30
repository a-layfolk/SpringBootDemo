package com.example.JwtDemo1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

class JwtDemo1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void createToken() {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND, 60);
		String token = JWT.create()
				.withClaim("userId", 21)		// payload
				.withClaim("username", "zhangsan")
				.withExpiresAt(instance.getTime())		// 指定令牌过期时间
				.sign(Algorithm.HMAC256("ASHGAHA%^A*D*(A^SD*AD"));	// 签名
		System.out.println(token);
	}

	@Test
	public void parseToken() {
		// 创建验证对象
		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ASHGAHA%^A*D*(A^SD*AD")).build();
		// 界面 token 加密过的数字
		DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTE5OTk2MjksInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.nC41N6Mn08QwVjYvAQmO7awBX1c0glwMVJltbgOM48M\n");

		System.out.println(verify.getClaim("userId").asInt());
		System.out.println(verify.getClaim("username").asString());
	}
}
