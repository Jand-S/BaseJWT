package com.github.jand.online.novel.api.controller;

import com.github.jand.online.novel.api.config.security.TokenService;
import com.github.jand.online.novel.api.controller.dto.TokenDto;
import com.github.jand.online.novel.api.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken loginInfo = loginForm.toToken();
        try {
            Authentication authentication = authenticationManager.authenticate(loginInfo);
            String token = tokenService.createToken(authentication);;
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
