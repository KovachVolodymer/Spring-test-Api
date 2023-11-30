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

   private static final String secretKey = "MySecretKeyKovachVolodymyrSecretKey";

   @Value("${jwt.expiration}")
   private long expiration;


   public String createToken(Map<String, Object> claims, String subject)
   {
       Date now = new Date();
       Date expirationDate = new Date(now.getTime() + expiration);

       Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

       return Jwts.builder()
               .setClaims(claims)
               .setSubject(subject)
               .setIssuedAt(now)
               .setExpiration(expirationDate)
               .signWith(signingKey)
               .compact();
   }

   public String generateToken(User user)
   {
       Map<String, Object> claims=new HashMap<>();
       claims.put("UserId",user.getId());
       claims.put("UserEmail",user.getEmail());

       return createToken(claims, user.getId());
   }

    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        return encoder.encode(password);
    }




}
