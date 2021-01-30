package com.example.JwtDemo2;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.JwtDemo2.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JwtDemo2ApplicationTests {

	@Test
	void contextLoads() {
	}

	private JwtUtils jwtUtils;

	@Test
	public void createToken() {
		Map<String, String> map = new HashMap<>();
		map.put("userId", "12");
		map.put("username", "lisi");
		map.put("password", "123456");

		String token = jwtUtils.getToken(map);
		System.out.println(token);
	}

	@Test
	public void verify() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTYxMjYwNjQwNSwidXNlcklkIjoiMTIiLCJ1c2VybmFtZSI6Imxpc2kifQ.938vpLKxWsrgGxRmpF_VsjUeb_JvEJE2QWBrGkZzUcM";
		DecodedJWT decodedJWT = jwtUtils.getTokenInfo(token);
		System.out.println(decodedJWT.getClaim("userId").asString());
		System.out.println(decodedJWT.getClaim("username").asString());
		System.out.println(decodedJWT.getClaim("password").asString());
	}
}
