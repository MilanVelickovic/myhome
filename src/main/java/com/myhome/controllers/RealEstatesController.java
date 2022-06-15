package com.myhome.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/realestates")
public class RealEstatesController {

    @GetMapping
    public ModelAndView getRealEstatesPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("realestates");
        return modelAndView;
    }

}
