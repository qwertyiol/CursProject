package com.netcracker.curs.fapi.misc;

import com.netcracker.curs.fapi.models.User;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String token;
    private User user;

    public JwtResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public JwtResponse() {

    }

    public String getToken() {
        return this.token;
    }

    public User getUser() {
        return this.user;
    }
}