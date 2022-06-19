package com.myhome.controllers;

import com.myhome.models.*;
import com.myhome.services.address.AddressService;
import com.myhome.services.advertisement.AdvertisementService;
import com.myhome.services.realEstate.RealEstateService;
import com.myhome.services.realEstateType.RealEstateTypeService;
import com.myhome.services.serviceType.ServiceTypeService;
import com.myhome.services.user.UserDetailsImpl;
import com.myhome.services.user.UserService;
import com.myhome.utils.AdvertisementCreateForm;
import com.myhome.utils.Converter;
import com.myhome.utils.HashCreator;
import lombok.RequiredArgsConstructor;
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
import java.time.Instant;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdvertisementsController {

    private final AdvertisementService advertisementService;

    private final AddressService addressService;

    private final RealEstateTypeService realEstateTypeService;

    private final RealEstateService realEstateService;

    private final ServiceTypeService serviceTypeService;


    @GetMapping(value = {"/", "/advertisements"})
    public ModelAndView getAdvertisementsPage() {

        List<Advertisement> advertisements = advertisementService.findAll();
        Converter converter = new Converter();
        HashCreator hashCreator = new HashCreator();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("converter", converter);
        modelAndView.addObject("hashCreator", hashCreator);
        modelAndView.addObject("advertisements", advertisements);
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

    @GetMapping("/advertisements/edit/{id}")
    public ModelAndView getAdvertisementEditPage(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("advertisementedit");
        return modelAndView;
    }

    @GetMapping("/advertisements/create")
    public ModelAndView getAdvertisementCreatePage() {

        AdvertisementCreateForm advertisementCreateForm = new AdvertisementCreateForm();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("advertisementCreateForm", advertisementCreateForm);
        modelAndView.setViewName("advertisementcreate");
        return modelAndView;
    }

    @PostMapping("/advertisements/create")
    public ModelAndView createAdvertisement(@Validated AdvertisementCreateForm advertisementCreateForm, BindingResult bindingResult) {

        User user =  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Address address = new Address();
        address.setId(null);
        address.setCountry(advertisementCreateForm.getCountry());
        address.setCity(advertisementCreateForm.getCity());
        address.setStreet(advertisementCreateForm.getStreet());
        address.setNumber(advertisementCreateForm.getNumber());

        Address createdAddress = addressService.save(address);

        RealEstate realEstate = new RealEstate();
        realEstate.setId(null);
        realEstate.setType(realEstateTypeService.findById(Integer.parseInt(advertisementCreateForm.getType())));
        realEstate.setAddress(address);
        realEstate.setSize(advertisementCreateForm.getSize());
        realEstate.setBedrooms(advertisementCreateForm.getBedrooms());
        realEstate.setBathrooms(advertisementCreateForm.getBathrooms());
        realEstate.setCarSpaces(advertisementCreateForm.getCarSpaces());

        RealEstate createdrealEstate = realEstateService.save(realEstate);

        Advertisement advertisement = new Advertisement();
        advertisement.setId(null);
        advertisement.setTitle(advertisementCreateForm.getTitle());
        advertisement.setDescription(advertisementCreateForm.getDescription());
        advertisement.setUser(user);
        advertisement.setRealEstate(realEstate);
        advertisement.setService(serviceTypeService.findById(Integer.parseInt(advertisementCreateForm.getService())));
        advertisement.setPrice(advertisementCreateForm.getPrice());
        advertisement.setPublishedOn(Instant.now());

        Advertisement createdAdvertisement = advertisementService.save(advertisement);

        HashCreator hashCreator = new HashCreator();

        createdAdvertisement = advertisementService.findByPublishedOn(advertisement.getPublishedOn());

        try {
            String path = Paths.get(".").toAbsolutePath().toString();
            path = path.substring(0, path.length() - 1) + "src/main/resources/static/fileServer/gallery/" + hashCreator.createHash(user.getEmail()) + "/" + hashCreator.createHash(createdAdvertisement.getId().toString());

            if (new File(path).mkdir()) {
                System.out.println("Advertisement directory created successfully!");
            } else {
                System.out.println("Failed to create advertisement directory!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("advertisementcreate");
        return modelAndView;
    }
}
