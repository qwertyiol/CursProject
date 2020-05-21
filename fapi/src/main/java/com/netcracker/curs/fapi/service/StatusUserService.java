package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.StatusUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatusUserService {
    List<StatusUser> getAll(HttpServletRequest request);
    StatusUser getStatusUserById(Integer id, HttpServletRequest request);
}
