package com.netcracker.curs.backend.controller;

import com.netcracker.curs.backend.entity.UserEntity;
import com.netcracker.curs.backend.service.UserService;
import com.netcracker.curs.backend.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserByUsernameAndPassword(@RequestParam String username,@RequestParam String password) {
        UserEntity user = userService.findByUsernameAndPassword(username, password);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "id") Integer id) {
        Optional<UserEntity> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<UserEntity> getAllPost() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public UserEntity saveUser(@RequestBody UserEntity user) throws MessagingException {
        String email = user.getEmail();

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        UserEntity userEntity = userService.save(user);

        String html = "<a href='"+"http://localhost:8080/api/user/confirm/"+ user.getId() + "'>Link to confirm account</a>";
        this.userService.SendEmail(email, html);

        return userEntity;
    }

    @RequestMapping(value = "confirm/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> confirmUser(@PathVariable(name = "id") Integer id) {
        Optional<UserEntity> userEntity = userService.getUserById(id);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            user.setIsConfirm(true);
            userService.save(user);
            String content = "<h1>User confirmed!</h1>";
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
        } else {
            String content = "<h1>User not confirmed check with admin!</h1>";
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }
}
