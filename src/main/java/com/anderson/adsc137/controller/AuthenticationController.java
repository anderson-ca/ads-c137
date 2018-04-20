package com.anderson.adsc137.controller;

import com.anderson.adsc137.model.Ad;
import com.anderson.adsc137.model.User;
import com.anderson.adsc137.repository.Ads;
import com.anderson.adsc137.repository.Users;
import com.anderson.adsc137.service.AdService;
import com.anderson.adsc137.service.UserDetailsLoader;
import com.anderson.adsc137.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthenticationController {

    private Users users;
    private UserDetailsLoader userDetailsLoader;
    private UserService userService;
    private AdService adservice;
    private Ads ads;


    public AuthenticationController(Users users, UserDetailsLoader userDetailsLoader, UserService userService, AdService adservice, Ads ads) {
        this.users = users;
        this.userDetailsLoader = userDetailsLoader;
        this.userService = userService;
        this.adservice = adservice;
        this.ads = ads;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// METHODS ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////
    // Method used to take user to login page
    ///////////////////////////////////////////
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    ///////////////////////////////////////////////////
    // Method displaying user profile dashboard.
    ///////////////////////////////////////////////////
    @GetMapping("/profile")
    public String showProfile(Model viewModel) {

        ////////////////////////////////////////////////////////
        // Getting user context to authenticate user session.
        ////////////////////////////////////////////////////////
        User user = userService.loggedInUser();
        List<Ad> adList= adservice.findByUserId(user.getId());

        viewModel.addAttribute("adList", adList);

        viewModel.addAttribute("user", user);


        /////////////////////////////////////////////////////
        //
        /////////////////////////////////////////////////////
        viewModel.addAttribute("user", user);

        /////////////////////////////////////////////////////
        // List of ads that have a relation with this user.
        /////////////////////////////////////////////////////

        return "users/profile";
    }
}
