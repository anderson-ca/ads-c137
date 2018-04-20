package com.anderson.adsc137.service;

import com.anderson.adsc137.model.User;
import com.anderson.adsc137.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Users users;

    public boolean isLoggedIn() {
        boolean isAnonymousUser = SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return !isAnonymousUser;
    }

    public User loggedInUser() {
        if (!isLoggedIn()) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Autowired
    public UserService(Users users) {
        this.users = users;
    }


    public Iterable<User> findAll() {
        return users.findAll();
    }


    public User save(User user) {
        return users.save(user);
    }


}
