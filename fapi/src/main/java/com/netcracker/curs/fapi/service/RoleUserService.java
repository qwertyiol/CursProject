package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.RoleUser;

import java.util.List;

public interface RoleUserService {
    List<RoleUser> getAll();
    RoleUser getRoleUserById(Integer id);
}
