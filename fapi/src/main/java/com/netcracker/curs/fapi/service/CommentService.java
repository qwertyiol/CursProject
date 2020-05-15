package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getCommentById(Integer id);
    Comment saveComment(Comment comment);
    void deleteComment(Integer idComment);
}
