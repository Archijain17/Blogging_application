package com.blog.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY=5*60*60;
	
	
	private String jwtSecret="0o6XCTy839cSxoBtaG9H0rifltk7fiWZXcjpZDvA1ZRuMmFoyNhZ7KjQ6d/QP1Yqjs9DSzQHRx9V1DzTov6mnw==";
	

	
	
	//retrieve username from jwtToken
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public Date getExpirationFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}


	 private Key getSigningKey() {
		   byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
	        return Keys.hmacShaKeyFor(keyBytes);

	    }
	 //for retrieving any info from token we will need secret key
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		try{
			return   Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
		}catch(ExpiredJwtException |MalformedJwtException| UnsupportedJwtException|IllegalArgumentException e) {
			throw new RuntimeException("invalid jwt token",e);
		}
		
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration=getExpirationFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
		
	}

	    // Generate token
	    public String doGenerateToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
	                .signWith(SignatureAlgorithm.HS512,jwtSecret)
	                .compact();
	    }

	
	
	// Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


	
}
