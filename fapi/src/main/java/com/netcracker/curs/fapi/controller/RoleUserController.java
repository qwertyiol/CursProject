package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.models.RoleUser;
import com.netcracker.curs.fapi.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roleUser")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @RequestMapping
    public ResponseEntity<List<RoleUser>> getAllRoleUser() {
        return ResponseEntity.ok(roleUserService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<RoleUser> getAllRoleUser(@PathVariable String id) throws InterruptedException {
        int roleUserId = Integer.parseInt(id);
        return ResponseEntity.ok(roleUserService.getRoleUserById(roleUserId));
    }
}