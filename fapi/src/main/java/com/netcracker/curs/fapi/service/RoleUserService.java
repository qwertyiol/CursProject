package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.RoleUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleUserService {
    List<RoleUser> getAll(HttpServletRequest request);
    RoleUser getRoleUserById(Integer id, HttpServletRequest request);
}
