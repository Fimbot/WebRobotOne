package com.example.webfimbot;

import com.example.webfimbot.dao.UserDao;
import com.example.webfimbot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserDao dao;

    @GetMapping("/rest/user")
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @PostMapping("/rest/user")
    public void createUser(@RequestBody User user) {
        dao.create(user);
    }
}
