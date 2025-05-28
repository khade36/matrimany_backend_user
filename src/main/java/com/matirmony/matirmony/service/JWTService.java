package com.matirmony.matirmony.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    public String generateToken(String email){
        Map<String,String> map=new HashMap<>();
        String jwtToken;
        jwtToken =Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"matrimony20242024matrimony20242024matrimony20242024").compact();
        return jwtToken;
    }
}
