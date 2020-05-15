package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.RoleUserEntity;

import java.util.Optional;

public interface RoleUserService {
    Optional<RoleUserEntity> getRoleUserById(Integer id);
    Iterable<RoleUserEntity> getAllRoleUser();
}
