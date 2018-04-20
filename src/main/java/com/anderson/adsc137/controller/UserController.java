package com.anderson.adsc137.controller;

import com.anderson.adsc137.model.Ad;
import com.anderson.adsc137.model.User;
import com.anderson.adsc137.repository.Ads;
import com.anderson.adsc137.repository.Users;
import com.anderson.adsc137.service.AdService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private Ads ads;
    private AdService adService;

    public UserController(Users users, PasswordEncoder passwordEncoder, Ads ads, AdService adService) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.ads = ads;
        this.adService = adService;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// METHODS ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    // Display index page to user.
    //////////////////////////////////////////////////////////
    @GetMapping("/")
    public String showHomePage() {

        return "index";
    }

    ////////////////////////////////////////////////////////
    // Registration page.
    ////////////////////////////////////////////////////////
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {

        model.addAttribute("user", new User());

        return "users/register";
    }

    ////////////////////////////////////////////////////////
    // Persist new user data to user table.
    ////////////////////////////////////////////////////////
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user,
                           Errors validation,
                           Model viewModel,
                           @RequestParam(name = "confirm-password") String confirmPassword) {

        ////////////////////////////////////////////////////////
        // Validating new user.
        ////////////////////////////////////////////////////////
        User existingUsername = users.findByUsername(user.getUsername());

        User existingEmail = users.findByEmail(user.getEmail());

        String invalidPassword = "";
        String invalidEmail = "";
        String invalidUsername = "";



        if ((existingUsername != null) || (user.getUsername().isEmpty())) {
            invalidUsername = "invalidUsername";
            validation.reject("test");
            System.out.println("Username error");
        }

        if ((existingEmail != null) || (user.getEmail().isEmpty())) {
            invalidEmail = "email error";
            validation.reject("test");
            System.out.println("Email error");
        }

        if (!confirmPassword.equals(user.getPassword())) {
            validation.reject("test");
            invalidPassword = "Password does not match confirm password";
            System.out.println("password doesn't match!");
        } else if(user.getPassword().isEmpty()) {
            validation.reject("test");
            invalidPassword = "Password field can't be empty";
            System.out.println("password field can't be empty");
        }


        if (validation.hasErrors()) {
            viewModel.addAttribute("invalidUsername", invalidUsername);
            viewModel.addAttribute("user", user);
            viewModel.addAttribute("invalidEmail", invalidEmail);
            viewModel.addAttribute("invalidPassword", invalidPassword);
            System.out.println("has error!");
            return "users/register";
        }

        String hash = passwordEncoder.encode(user.getPassword());

        user.setPassword(hash);

        users.save(user);

        /////////////////////////////////////////////////////////////////////
        // Redirect user to login page in order to login with new account.
        /////////////////////////////////////////////////////////////////////
        return "users/profile";
    }


}
