package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.models.StatusUser;
import com.netcracker.curs.fapi.service.StatusUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class StatusUserServiceImpl implements StatusUserService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<StatusUser> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        StatusUser[] statusUsersResponse = restTemplate.getForObject(backendServerUrl + "/api/statusUser", StatusUser[].class);
        return statusUsersResponse == null ? Collections.emptyList() : Arrays.asList(statusUsersResponse);
    }

    @Override
    public StatusUser getStatusUserById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "/api/statusUser" + id, StatusUser.class);
    }
}