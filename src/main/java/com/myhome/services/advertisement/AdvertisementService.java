package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;

import java.util.List;

public interface AdvertisementService {

    public Advertisement findById(Integer id);

    public List<Advertisement> findAll();

}
