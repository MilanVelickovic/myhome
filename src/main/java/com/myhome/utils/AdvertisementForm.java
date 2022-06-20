package com.myhome.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisementForm {

    // Advertisements attributes
    private Integer advertisementId;
    private String title;
    private String description;
    private String service;
    private Long price;
    private String images;

    // Real estate attributes
    private Integer realEstateId;
    private String type;
    private Integer size;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer carSpaces;

    // Address details
    private Integer addressId;
    private String country;
    private String city;
    private String street;
    private String number;

    public AdvertisementForm(Integer advertisementId, String title, String description, String service, Long price, String images,
                             Integer realEstateId, String type, Integer size, Integer bedrooms, Integer bathrooms, Integer carSpaces,
                             Integer addressId, String country, String city, String street, String number) {
        this.advertisementId = advertisementId;
        this.title = title;
        this.description = description;
        this.service = service;
        this.price = price;
        this.images = images;
        this.realEstateId = realEstateId;
        this.type = type;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.carSpaces = carSpaces;
        this.addressId = addressId;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public AdvertisementForm() {

    }
}
