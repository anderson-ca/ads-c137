package com.anderson.adsc137.controller;

import com.anderson.adsc137.model.Ad;
import com.anderson.adsc137.model.User;
import com.anderson.adsc137.repository.Ads;
import com.anderson.adsc137.repository.Users;
import com.anderson.adsc137.service.AdService;
import com.anderson.adsc137.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdController {
    private Users users;
    private Ads ads;
    private AdService adService;
    private UserService usersService;

    public AdController(Users users, Ads ads, AdService adService, UserService usersService) {
        this.users = users;
        this.ads = ads;
        this.adService = adService;
        this.usersService = usersService;
    }

    @GetMapping("/create")
    public String showCreateAdForm(Model viewModel) {

        viewModel.addAttribute("ad", new Ad());

        return "ads/create";
    }

    @PostMapping("/create")
    public String createAd(@ModelAttribute Ad ad,
                           Errors validation,
                           Model viewModel,
                           RedirectAttributes redirect
    ) {

        ////////////////////////////////
        // Validate user.
        ////////////////////////////////
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("ad", ad);
            return "ads/create";
        }

        /////////////////////////////////////////////////////////////////
        // If user is validated create ad and redirect user to dashboard
        /////////////////////////////////////////////////////////////////

        User user = usersService.loggedInUser();
        ad.setUser(user);
        adService.saveAd(ad);

        return "redirect:/profile";
    }

    @GetMapping("/show")
    public String showAllAds(Model viewModel, Ad ad) {

        User user = usersService.loggedInUser();
        List<Ad> allAds = (List<Ad>) adService.findAll();

        viewModel.addAttribute("allAds", allAds);

        viewModel.addAttribute("user", user);

        return "ads/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable long id, Model viewModel) {

        Ad ad = adService.findById(id);

        viewModel.addAttribute("ad", ad);

        return "ads/edit";
    }

    @PostMapping("/edit")
    public String updateAd(@ModelAttribute Ad ad,
                           @RequestParam(name = "ad_id") long ad_id) {

        User user = usersService.loggedInUser();

        ad.setId(ad_id);

        ad.setUser(user);

        ads.save(ad);

        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteAd(@ModelAttribute Ad ad,
                           @RequestParam(name = "ad_id_del") long ad_id_del) {

        adService.delete(ad_id_del);

        return "redirect:/profile";
    }

}
