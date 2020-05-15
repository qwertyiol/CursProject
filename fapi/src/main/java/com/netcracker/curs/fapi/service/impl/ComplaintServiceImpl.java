package com.netcracker.curs.fapi.service.impl;

import com.netcracker.curs.fapi.models.Complaint;
import com.netcracker.curs.fapi.service.ComplaintService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ComplaintServiceImpl implements ComplaintService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Complaint> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Complaint[] postResponse = restTemplate.getForObject(backendServerUrl + "/api/complaint/", Complaint[].class);
        return postResponse == null ? Collections.emptyList() : Arrays.asList(postResponse);
    }

    @Override
    public Complaint getComplaintById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/complaint/" + id, Complaint.class);
    }

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "api/complaint/", complaint, Complaint.class).getBody();
    }

    @Override
    public void deleteComplaint(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "api/complaint/" + id);
    }

    @Override
    public void deletePost(Integer idPost) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/post/" + idPost);
    }
}