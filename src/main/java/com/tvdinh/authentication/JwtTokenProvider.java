package com.tvdinh.authentication;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtTokenProvider {
	
	private static final Logger logger=Logger.getLogger(JwtTokenProvider.class);
	
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private final String JWT_SECRET = "jwt_dinh_1008";

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 604800000L;
	
	
	//Tạo chuỗi jwt từ thông tin của user
	public String generateJwtToken(Authentication authentication) {
		MyUser userPrincipal=(MyUser) authentication.getPrincipal();
	
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setExpiration(new Date((new Date()).getTime()+ JWT_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}
	
	//Lấy thông tin username
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
	}
	
	//Kiểm tra thông tin token
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		}catch (SignatureException e) {
			logger.error("Invalid JWT signature: "+ e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: "+ e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: "+ e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: "+ e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: "+ e.getMessage());
		}
		
		return false;
	}
	
}
