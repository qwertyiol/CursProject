package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.Complaint;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ComplaintService {
    Complaint getComplaintById(Integer id, HttpServletRequest request);
    Complaint saveComplaint(Complaint post, HttpServletRequest request);
    void deleteComplaint(Integer id, HttpServletRequest request);
    void deletePost(Integer id, HttpServletRequest request);
    List<Complaint> getAll(HttpServletRequest request);

}
