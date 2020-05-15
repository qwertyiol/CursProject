package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.StatusUserEntity;

import java.util.Optional;

public interface StatusUserService {
    Optional<StatusUserEntity> getStatusUserById(Integer id);
    Iterable<StatusUserEntity> getAllStatusUser();
}
