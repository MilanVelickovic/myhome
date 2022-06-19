package com.myhome.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisementCreateForm {

    // Advertisements attributes
    private String title;
    private String description;
    private String service;
    private Long price;
    private String images;

    // Real estate attributes
    private String type;
    private Integer size;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer carSpaces;

    // Address details
    private String country;
    private String city;
    private String street;
    private String number;

    public AdvertisementCreateForm(String title, String description, String service, Long price, String images,
                                   String type, Integer size, Integer bedrooms, Integer bathrooms, Integer carSpaces,
                                   String country, String city, String street, String number) {
        this.title = title;
        this.description = description;
        this.service = service;
        this.price = price;
        this.images = images;
        this.type = type;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.carSpaces = carSpaces;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public AdvertisementCreateForm() {

    }

    @Override
    public String toString() {
        return "AdvertisementCreateForm{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", service='" + service + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", carSpaces=" + carSpaces +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
