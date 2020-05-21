package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    List<Comment> getAll(HttpServletRequest request);
    Comment getCommentById(Integer id, HttpServletRequest request);
    Comment saveComment(Comment comment, HttpServletRequest request);
    void deleteComment(Integer idComment, HttpServletRequest request);
}
