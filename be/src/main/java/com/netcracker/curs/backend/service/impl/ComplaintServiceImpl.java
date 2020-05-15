package com.netcracker.curs.backend.service.impl;

import com.netcracker.curs.backend.repository.ComplaintRepository;
import com.netcracker.curs.backend.entity.ComplaintEntity;
import com.netcracker.curs.backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ComplaintServiceImpl implements ComplaintService {

    private ComplaintRepository repository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComplaintEntity saveComplaint(ComplaintEntity complaint) {
        return repository.save(complaint);
    }

    @Override
    public Optional<ComplaintEntity> getComplaintById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ComplaintEntity> getAllComplaint() {
        return repository.findAll();
    }

    @Override
    public void deleteComplaint(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deletePost(Integer idPost) {
        repository.deleteById(idPost);
    }
}