package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.LikePostEntity;
import com.netcracker.curs.backend.entity.UserEntity;

import java.util.Optional;

public interface LikeService {
    LikePostEntity saveLike(LikePostEntity like);
    Optional<LikePostEntity> getLikeById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<LikePostEntity> getAllLike();
    void deleteLike(Integer id);
}