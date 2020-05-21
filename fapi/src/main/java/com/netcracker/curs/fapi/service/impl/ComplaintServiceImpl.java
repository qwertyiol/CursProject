package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.interseptors.AuthInterceptor;
import com.netcracker.curs.fapi.models.Complaint;
import com.netcracker.curs.fapi.service.ComplaintService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class ComplaintServiceImpl implements ComplaintService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Complaint> getAll(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        Complaint[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/complaint/", Complaint[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Complaint getComplaintById(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.getForObject(backendServerUrl + "api/complaint/" + id, Complaint.class);
    }

    @Override
    public Complaint saveComplaint(Complaint complaint, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        return restTemplate.postForEntity(backendServerUrl + "api/complaint/", complaint, Complaint.class).getBody();
    }

    @Override
    public void deleteComplaint(Integer id, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        restTemplate.delete(backendServerUrl + "api/complaint/" + id);
    }

    @Override
    public void deletePost(Integer idPost, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        AuthInterceptor interceptor = new AuthInterceptor(request.getHeader("Authorization"));
        restTemplate.setInterceptors(singletonList(interceptor));
        restTemplate.delete(backendServerUrl + "/api/post/" + idPost);
    }
}