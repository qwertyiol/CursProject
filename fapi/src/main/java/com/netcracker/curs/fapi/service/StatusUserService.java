package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.StatusUser;

import java.util.List;

public interface StatusUserService {
    List<StatusUser> getAll();
    StatusUser getStatusUserById(Integer id);
}
