package com.wipro.flightservices.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest httprequest = (HttpServletRequest) request;
        final HttpServletResponse httpresponse = (HttpServletResponse) response;
        final String authHeader = httprequest.getHeader("authorization");
        
        if ("OPTIONS".equals(httprequest.getMethod())) {
        	httpresponse.setStatus(HttpServletResponse.SC_OK);
        	chain.doFilter(httprequest, httpresponse);
        } else {
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("An exception occurred");
            }  
        }
        
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        request.setAttribute("claims", claims);
        request.setAttribute("blog", request.getParameter("id"));
        chain.doFilter(request, response);
		
	}

}
