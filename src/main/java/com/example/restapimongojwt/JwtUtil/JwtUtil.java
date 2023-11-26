package com.example.restapimongojwt.JwtUtil;

import com.example.restapimongojwt.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtil {

   @Value("${jwt.secret}")
   private String secretKey;

   @Value("${jwt.expiration}")
   private long expiration;


   public static String createToken(Map<String, Object> claims, String subject)
   {
      Date now =new Date();
      Date expirationData= new Date(now.getTime()+expiration);

      Key signingKey=new SecretKeySpec(secretKey.getBytes(),SignatureAlgorithm.ES256.getJcaName());

      return Jwts.builder()
              .setClaims(claims)
              .setSubject(subject)
              .setIssuedAt(now)
              .setExpiration(expirationData)
              .signWith(signingKey)
              .compact();
   }

   public static String generateToken(User user)
   {
       Map<String, Object> claims=new HashMap<>();
       claims.put("UserId",user.getId());
       claims.put("UserEmail",user.getEmail());

       return createToken(claims, String.valueOf(user.getId()));
   }

    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        return encoder.encode(password);
    }




}
