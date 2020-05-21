package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.misc.JwtResponse;
import com.netcracker.curs.fapi.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    List<User> getAll(HttpServletRequest request);
    User getUserById(Integer id, HttpServletRequest request);
    User save(User user, HttpServletRequest request);
    void deleteUser(Integer id, HttpServletRequest request);
    JwtResponse authenticate(String userName, String password);
}
