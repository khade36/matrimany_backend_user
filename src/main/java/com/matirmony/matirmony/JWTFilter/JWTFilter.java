package com.matirmony.matirmony.JWTFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        final String authHeader= request.getHeader("Authorization");
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else if(authHeader==null || !authHeader.startsWith("Bearer")) {
              throw new ServletException("invalid token");
            }
        String token=authHeader.substring(7);
        Claims claims= Jwts.parser().setSigningKey("matrimony20242024matrimony20242024matrimony20242024").parseClaimsJws(token).getBody();
        request.setAttribute("claims",claims);
        filterChain.doFilter(request,response);
        }
}
