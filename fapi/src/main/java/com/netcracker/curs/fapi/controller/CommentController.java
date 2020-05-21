package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.models.Comment;
import com.netcracker.curs.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public ResponseEntity<List<Comment>> getAllComment() {
        return ResponseEntity.ok(commentService.getAll(request));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment /*todo server validation*/) {
        if (comment != null) {
            return ResponseEntity.ok(commentService.saveComment(comment, request));
        }
        return null;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable String id) {
        commentService.deleteComment(Integer.valueOf(id), request);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) throws InterruptedException {
        int commentId = Integer.valueOf(id);
        return ResponseEntity.ok(commentService.getCommentById(commentId, request));
    }
}