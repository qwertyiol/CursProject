package com.netcracker.curs.backend.service.impl;

import com.netcracker.curs.backend.repository.UserRepository;
import com.netcracker.curs.backend.entity.UserEntity;
import com.netcracker.curs.backend.service.UserService;
import com.netcracker.curs.backend.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Optional;
import java.util.Properties;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer userByIdUser) {
        return repository.findById(userByIdUser);
    }

    @Override
    public Iterable<UserEntity> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void SendEmail(String toEmail, String message) {
        final String fromEmail = "nastyachernyak4@gmail.com"; //requires valid gmail id
        final String password = "7726493iI"; // correct password for gmail id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, toEmail, message, message);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }
}