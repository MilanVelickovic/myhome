package com.myhome.controllers;

import com.myhome.models.*;
import com.myhome.services.address.AddressService;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.services.realEstate.RealEstateService;
import com.myhome.services.realEstateType.RealEstateTypeService;
import com.myhome.services.serviceType.ServiceTypeService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.utils.AdvertisementForm;
import com.myhome.utils.Converter;
import com.myhome.utils.Countries;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class AdvertisementsController {

    private final AdvertisementService advertisementService;

    private final AddressService addressService;

    private final RealEstateTypeService realEstateTypeService;

    private final ServiceTypeService serviceTypeService;


    @GetMapping(value = {"/", "/advertisements"})
    public ModelAndView getAdvertisementsPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("converter", new Converter());
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.addObject("advertisements", advertisementService.findAll());
        modelAndView.setViewName("advertisements");
        return modelAndView;
    }

    @GetMapping("/advertisements/{id}")
    public ModelAndView getAdvertisementPage(@PathVariable Integer id) {

        Advertisement advertisement = advertisementService.findById(id);

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
        modelAndView.setViewName("advertisement");
        return modelAndView;
    }

    @GetMapping("/advertisements/create")
    public ModelAndView getAdvertisementCreatePage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("advertisementForm", new AdvertisementForm());
        modelAndView.addObject("countries", new Countries().loadCountryNamesFromJSON());
        modelAndView.addObject("services", serviceTypeService.findAll());
        modelAndView.addObject("types", realEstateTypeService.findAll());
        modelAndView.setViewName("advertisementcreate");
        return modelAndView;
    }

    @PostMapping("/advertisements/create")
    public ModelAndView createAdvertisement(@Validated AdvertisementForm advertisementForm) {

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        advertisementService.createAdvertisement(advertisementForm);

        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.addObject("converter", new Converter());
        modelAndView.addObject("advertisements", advertisementService.findByUser(user));
        return modelAndView;
    }

    @GetMapping("/advertisements/edit/{id}")
    public ModelAndView getAdvertisementEditPage(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("advertisement", advertisementService.findById(id));
        modelAndView.addObject("advertisementForm", new AdvertisementForm());
        modelAndView.addObject("countries", new Countries().loadCountryNamesFromJSON());
        modelAndView.addObject("services", serviceTypeService.findAll());
        modelAndView.addObject("types", realEstateTypeService.findAll());
        modelAndView.setViewName("advertisementedit");
        return modelAndView;
    }

    @PostMapping("/advertisements/edit")
    public ModelAndView editAdvertisement(@Validated AdvertisementForm advertisementForm) {

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        advertisementService.updateAdvertisement(advertisementForm);

        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        modelAndView.addObject("advertisements", advertisementService.findByUser(user));
        modelAndView.addObject("user", user);
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.addObject("converter", new Converter());
        return modelAndView;
    }

    @GetMapping("/advertisements/delete/{id}")
    public ModelAndView getAdvertisementDeletePage(@PathVariable Integer id) {

        Advertisement advertisement = advertisementService.findById(id);

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");

        if ((advertisement != null && user.getId() == advertisement.getUser().getId()) || user.getRole().getName().equals("Admin")) {

            modelAndView.addObject("advertisementId", advertisement.getId());
            modelAndView.setViewName("advertisementdelete");

        }

        return modelAndView;
    }

    @PostMapping("/advertisements/delete/{id}")
    public ModelAndView deleteAdvertisement(@PathVariable Integer id) {

        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Advertisement advertisement = advertisementService.findById(id);

        addressService.deleteById(advertisement.getRealEstate().getAddress().getId());

        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        modelAndView.addObject("advertisements", advertisementService.findByUser(user));
        modelAndView.addObject("user", user);
        modelAndView.addObject("hashCreator", new HashCreator());
        modelAndView.addObject("converter", new Converter());
        return modelAndView;
    }

}
