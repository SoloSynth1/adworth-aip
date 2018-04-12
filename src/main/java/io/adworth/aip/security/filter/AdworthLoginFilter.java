package io.adworth.aip.security.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.adworth.aip.helper.ResponseMessage;
import io.adworth.aip.security.constant.SecretKey;
import io.adworth.aip.security.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AdworthLoginFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

    public AdworthLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    	try {
    		if (req.getMethod().equals(HttpMethod.POST.name())) {
	            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            user.getUsername(),
	                            user.getPassword(),
	                            new ArrayList<>())
	            );
    		}
    		return null;
        } catch (IOException e) {
        	// treat any bad request at "/login" as no username and password passed
        	return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, null));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
    	String token = Jwts.builder()
                .setSubject(auth.getName())
                // set token to be expired in 120 seconds.
                .setExpiration(new Date(System.currentTimeMillis() + 120 * 1000)) 
                .signWith(SignatureAlgorithm.HS512, SecretKey.SIGNING_KEY) 
                .compact();
    	ResponseMessage.setSecurityResponse(res, "Login Sucessful", null, token, HttpStatus.OK); 
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
    	ResponseMessage.setSecurityResponse(res, "Login Failed", e.getLocalizedMessage(), null, HttpStatus.FORBIDDEN);
    }

}
