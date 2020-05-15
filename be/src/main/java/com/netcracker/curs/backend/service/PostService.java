package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.PostEntity;
import com.netcracker.curs.backend.entity.UserEntity;

import java.util.Optional;

public interface PostService {
    PostEntity savePost(PostEntity post);
    Optional<PostEntity> getPostById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<PostEntity> getAllPost();
    void deletePost(Integer id);
}