package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.models.Comment;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class CommentServiceImpl implements CommentService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Comment> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));

        Comment[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/comment/", Comment[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Comment getCommentById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));

        return restTemplate.getForObject(backendServerUrl + "/api/comment/" + id, Comment.class);
    }

    @Override
    public Comment saveComment(Comment comment, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));

        if (comment.getUserByIdUser() == null) {
            User user = restTemplate.getForObject(backendServerUrl + "/api/user/" + comment.getIdUser(), User.class);
            comment.setUserByIdUser(user);
        }
        return restTemplate.postForEntity(backendServerUrl + "/api/comment", comment, Comment.class).getBody();
    }

    @Override
    public void deleteComment(Integer id, HttpServletRequest request) {

        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));

        restTemplate.delete(backendServerUrl + "/api/comment/" + id);
    }
}