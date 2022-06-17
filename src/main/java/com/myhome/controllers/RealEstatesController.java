package com.myhome.controllers;

import com.myhome.models.*;
import com.myhome.repository.AdvertisementRepository;
import com.myhome.utils.Converter;
import com.myhome.utils.HashCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RealEstatesController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @GetMapping("/realestates")
    public ModelAndView getRealEstatesPage() {

        List<Advertisement> advertisements = advertisementRepository.findAll();
        Converter converter = new Converter();
        HashCreator hashCreator = new HashCreator();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("converter", converter);
        modelAndView.addObject("hashCreator", hashCreator);
        modelAndView.addObject("advertisements", advertisements);
        modelAndView.setViewName("realestates");
        return modelAndView;
    }

    @GetMapping("/realestates/{id}")
    public ModelAndView getRealEstatePage(@PathVariable Integer id) {

        Advertisement advertisement = advertisementRepository.getAdvertisementById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("advertisement", advertisement);
        modelAndView.setViewName("realestate");
        return modelAndView;
    }

}
