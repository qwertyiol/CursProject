package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.ComplaintEntity;

import java.util.Optional;

public interface ComplaintService {
    ComplaintEntity saveComplaint(ComplaintEntity complaint);
    Optional<ComplaintEntity> getComplaintById(Integer id);
    void deleteComplaint(Integer id);
    void deletePost(Integer idPost);
    Iterable<ComplaintEntity> getAllComplaint();
}