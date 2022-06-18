package com.myhome.controllers;

import com.myhome.models.*;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.utils.Converter;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RealEstatesController {

    private final AdvertisementService advertisementService;

    @GetMapping("/realestates")
    public ModelAndView getRealEstatesPage() {

        List<Advertisement> advertisements = advertisementService.findAll();
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
    //@GetMapping("/realestatess")
    public ModelAndView getRealEstatePage(@PathVariable Integer id) {
    //public ModelAndView getRealEstatePage() {

        Advertisement advertisement = advertisementService.findById(id).get();

        Converter converter = new Converter();
        HashCreator hashCreator = new HashCreator();

        String userFolder = "";
        String advertisementFolder = "";

        try {
            userFolder = hashCreator.createHash(advertisement.getUser().getEmail());
            advertisementFolder = hashCreator.createHash(advertisement.getId().toString());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        String path = Paths.get(".").toAbsolutePath().toString();
        path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + userFolder + "/" + advertisementFolder;

        int numOfImages = new File(path).list().length;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("advertisement", advertisement);
        modelAndView.addObject("converter", converter);
        modelAndView.addObject("userFolder", userFolder);
        modelAndView.addObject("advertisementFolder", advertisementFolder);
        modelAndView.addObject("numOfImages", numOfImages);
        modelAndView.setViewName("realestate");
        return modelAndView;
    }

}
