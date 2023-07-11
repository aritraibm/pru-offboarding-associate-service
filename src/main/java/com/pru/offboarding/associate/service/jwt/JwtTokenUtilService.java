package com.pru.offboarding.associate.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;

@Service
public class JwtTokenUtilService {

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	public User getUserDetails(String token){
		Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
		User userDetails = new User();
		for (Map.Entry<String, Object> entry : claims.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			if(key.equalsIgnoreCase("sub")) {
				String s1=(String)val;
				String[] jwtSubject = s1.split(",");
				userDetails.setId(jwtSubject[0]);
				userDetails.setEmail(jwtSubject[1]);
			}
			if(key.equalsIgnoreCase("roles")) {
				String roles = (String)val;
				roles = roles.replace("[", "").replace("]", "");
				String[] roleNames = roles.split(",");
				for (String aRoleName : roleNames) {
					userDetails.addRole(new Role(aRoleName));
				}
			}
			
		}
		
		return userDetails;
	}
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}