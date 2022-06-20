package com.myhome.controllers;

import com.myhome.models.Advertisement;
import com.myhome.models.User;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.utils.Converter;
import com.myhome.utils.Countries;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final AdvertisementService advertisementService;

    @GetMapping("/profile/edit")
    public ModelAndView getEditPage() {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profileedit");
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage() {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        String country = new Countries().getCountryNameByDialCodeFromJSON(user.getPhoneNumber().substring(0, user.getPhoneNumber().indexOf(' ')));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("country", country);
        modelAndView.addObject("advertisements", advertisementService.findByUser(user));
        modelAndView.addObject("converter", new Converter());
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.setViewName("profile");
        return modelAndView;
    }

}
