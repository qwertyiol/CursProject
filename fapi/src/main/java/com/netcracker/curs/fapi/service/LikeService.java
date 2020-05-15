package com.netcracker.curs.fapi.service;
import com.netcracker.curs.fapi.models.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAll();
    Like getLikeById(Integer id);
    Like saveLike(Like like);
    void deleteLike(Integer id);
}
