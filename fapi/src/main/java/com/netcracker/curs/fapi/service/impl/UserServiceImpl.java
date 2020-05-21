package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.misc.JwtRequest;
import com.netcracker.curs.fapi.misc.JwtResponse;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class UserServiceImpl implements UserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public User save(User user, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.postForEntity(backendServerUrl + "/api/user/registration", user, User.class).getBody();
    }

    @Override
    public List<User> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user", User[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public User getUserById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "api/user/id?id=" + id, User.class);
    }

    @Override
    public void deleteUser(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        restTemplate.delete(backendServerUrl + "api/user/id?id=" + id);
    }

    @Override
    public JwtResponse authenticate(String userName, String password) {
        RestTemplate restTemplate = new RestTemplate();
        JwtRequest jwtRequest = new JwtRequest(userName, password);
        return restTemplate.postForEntity(backendServerUrl + "authenticate",jwtRequest, JwtResponse.class).getBody();
    }
}