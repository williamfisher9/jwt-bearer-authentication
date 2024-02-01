package com.apps.securityapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") != null){
            String jwtToken = jwtUtils.parseAuthorizationHeader(request);
            try {
                jwtUtils.validateToken(jwtToken);
                setSecurityContext(jwtToken);
            } catch (Exception e){
                LOG.error("ERROR IN JWT TOKEN {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(String jwtToken) {
        String username = jwtUtils.getUsernameFromToken(jwtToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }


}
