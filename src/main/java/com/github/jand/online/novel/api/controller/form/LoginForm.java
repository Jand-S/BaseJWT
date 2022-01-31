package com.github.jand.online.novel.api.controller.form;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginForm {

    private String username;
    private String password;


    public UsernamePasswordAuthenticationToken  toToken() {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
