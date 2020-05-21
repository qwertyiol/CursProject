package com.netcracker.curs.backend.misc;

import com.netcracker.curs.backend.entity.UserEntity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final UserEntity user;

    public JwtResponse(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public UserEntity getUser() {
        return this.user;
    }
}