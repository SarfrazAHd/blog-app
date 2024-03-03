package com.app.blogapplication.JWT;

import com.app.blogapplication.Services.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTUtil tokenUtil;

    @Autowired
    private final CustomUserDetailService userDetailService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (!(request.getRequestURI().contains("/api/blog-app/security/token"))) {
            if (tokenHeader != null && tokenHeader.startsWith("Bearer")) {
                token = tokenHeader.substring(7);
                if (token != null) {
                    username = tokenUtil.getUsernameFromToken(token);

                    UserDetails userDetails = userDetailService.loadUserByUsername(username);

                    if (tokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    } else {
                        throw new RuntimeException("Invalid Token, please try with new token");
                    }
                } else {
                    throw new NullPointerException("Token Null, Authorization header doesn't contain token");
                }
            } else {
                throw new RuntimeException("Authorization header is not present in header..");
            }
            filterChain.doFilter(request, response);
        } else filterChain.doFilter(request, response);
    }
}

