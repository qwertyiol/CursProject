package com.netcracker.curs.fapi.controller;

import com.netcracker.curs.fapi.misc.JwtResponse;
import com.netcracker.curs.fapi.models.User;
import com.netcracker.curs.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAll(request));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public JwtResponse authenticate(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password);
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user) {
        return userService.save(user, request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(Integer.valueOf(id), request);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws InterruptedException {
        int userId = Integer.parseInt(id);
        return ResponseEntity.ok(userService.getUserById(userId, request));
    }
}