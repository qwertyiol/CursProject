package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.models.RoleUser;
import com.netcracker.curs.fapi.service.RoleUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class RoleUserServiceImpl implements RoleUserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<RoleUser> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        RoleUser[] roleUsersResponse = restTemplate.getForObject(backendServerUrl + "/api/roleUser", RoleUser[].class);
        return roleUsersResponse == null ? Collections.emptyList() : Arrays.asList(roleUsersResponse);
    }

    @Override
    public RoleUser getRoleUserById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "/api/roleUser" + id, RoleUser.class);
    }
}