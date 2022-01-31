package com.github.jand.online.novel.api.controller.dto;

import lombok.Getter;

@Getter
public class TokenDto {
    private String token;
    private String type;
    public TokenDto(String token, String bearer) {
        this.token = token;
        this.type = bearer;
    }
}
