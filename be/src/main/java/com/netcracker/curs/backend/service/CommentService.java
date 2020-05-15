package com.netcracker.curs.backend.service;

import com.netcracker.curs.backend.entity.CommentEntity;
import com.netcracker.curs.backend.entity.UserEntity;

import java.util.Optional;

public interface CommentService {
    CommentEntity saveComment(CommentEntity comment);
    Optional<CommentEntity> getCommentById(Integer id);
    Optional<UserEntity> getUserById(Integer userByIdUser);
    Iterable<CommentEntity> getAllComment();
    void deleteComment(Integer id);
}