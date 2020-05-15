package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.models.StatusUser;
import com.netcracker.curs.fapi.service.StatusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statusUser")
public class StatusUserController {
    @Autowired
    private StatusUserService statusUserService;

    @RequestMapping
    public ResponseEntity<List<StatusUser>> getAllStatusUser() {
        return ResponseEntity.ok(statusUserService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<StatusUser> getAllStatusUser(@PathVariable String id) throws InterruptedException {
        int statusUserId = Integer.parseInt(id);
        return ResponseEntity.ok(statusUserService.getStatusUserById(statusUserId));
    }
}