package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(Integer id);
    User getByUsernameAndPassword(String username, String password);
    User save(User user);
    void deleteUser(Integer id);
}
