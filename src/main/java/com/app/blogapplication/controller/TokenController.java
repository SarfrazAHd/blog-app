package com.app.blogapplication.controller;


import com.app.blogapplication.JWT.JWTUtil;
import com.app.blogapplication.Services.CustomUserDetailService;
import com.app.blogapplication.pojo.TokenRequest;
import com.app.blogapplication.pojo.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog-app/security")
@RequiredArgsConstructor
public class TokenController {
    private final JWTUtil jwtUtil;

    private final CustomUserDetailService customUserDetailService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity generateToken(@RequestBody TokenRequest request) {
        try{
            UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUserName());
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            TokenResponse token = new TokenResponse(jwtUtil.generateToken(userDetails));
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
