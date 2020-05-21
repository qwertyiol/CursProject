package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.models.Post;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class PostServiceImpl implements PostService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Post> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        Post[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/post/", Post[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Post getPostById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "/api/post/" + id, Post.class);
    }

    @Override
    public Post  savePost(Post post, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        if (post.getUserByIdUser() == null) {
            User user = restTemplate.getForObject(backendServerUrl + "/api/user/" + post.getIdUser(), User.class);
            post.setUserByIdUser(user);
        }
        return restTemplate.postForEntity(backendServerUrl + "/api/post", post, Post.class).getBody();
    }

    @Override
    public void deletePost(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        restTemplate.delete(backendServerUrl + "/api/post/" + id);
    }
}