package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.service.LikeService;
import com.netcracker.curs.fapi.models.Like;
import com.netcracker.curs.fapi.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class LikeServiceImpl implements LikeService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Like> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        Like[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/like/", Like[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Like getLikeById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "/api/like/" + id, Like.class);
    }

    @Override
    public Like saveLike(Like like, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        User user =  restTemplate.getForObject(backendServerUrl + "/api/user/" + like.getIdUser(), User.class);
        like.setUserByIdUser(user);
        return restTemplate.postForEntity(backendServerUrl + "/api/like", like, Like.class).getBody();
    }

    @Override
    public void deleteLike(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        restTemplate.delete(backendServerUrl + "/api/like/" + id);
    }
}