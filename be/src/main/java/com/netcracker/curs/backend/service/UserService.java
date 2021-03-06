package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity save(UserEntity post);
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
    Optional<UserEntity> getUserById(Integer id);
    Iterable<UserEntity> getAllUser();
    void deleteUser(Integer id);
    void SendEmail(String toEmail, String message);
}
