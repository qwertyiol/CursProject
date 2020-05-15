package com.netcracker.curs.backend.service.impl;

import com.netcracker.curs.backend.repository.StatusUserRepository;
import com.netcracker.curs.backend.service.StatusUserService;
import com.netcracker.curs.backend.entity.StatusUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusUserServiceImpl implements StatusUserService {

    private StatusUserRepository repository;

    @Autowired
    public StatusUserServiceImpl(StatusUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StatusUserEntity> getStatusUserById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<StatusUserEntity> getAllStatusUser() {
        return repository.findAll();
    }
}
