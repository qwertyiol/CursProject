package com.netcracker.curs.fapi.service;

import com.netcracker.curs.fapi.models.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostService {
    List<Post> getAll(HttpServletRequest request);
    Post getPostById(Integer id, HttpServletRequest request);
    Post savePost(Post post, HttpServletRequest request);
    void deletePost(Integer idPost, HttpServletRequest request);
}
