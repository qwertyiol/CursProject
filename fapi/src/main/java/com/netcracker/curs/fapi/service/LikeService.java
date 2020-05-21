package com.netcracker.curs.fapi.service;
import com.netcracker.curs.fapi.models.Like;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LikeService {
    List<Like> getAll(HttpServletRequest request);
    Like getLikeById(Integer id, HttpServletRequest request);
    Like saveLike(Like like, HttpServletRequest request);
    void deleteLike(Integer id, HttpServletRequest request);
}
