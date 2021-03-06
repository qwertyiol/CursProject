package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.service.LikeService;
import com.netcracker.curs.fapi.models.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public ResponseEntity<List<Like>> getAllLike() {
        return ResponseEntity.ok(likeService.getAll(request));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Like> saveLike(@RequestBody Like like /*todo server validation*/) {
        if (like != null) {
            return ResponseEntity.ok(likeService.saveLike(like, request));
        }
        return null;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLike(@PathVariable String id) {
        likeService.deleteLike(Integer.valueOf(id), request);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable String id) throws InterruptedException {
        int likeId = Integer.valueOf(id);
        return ResponseEntity.ok(likeService.getLikeById(likeId, request));
    }
}
