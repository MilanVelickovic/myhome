package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {

    public List<Advertisement> findAll();

    public Optional<Advertisement> findById(Integer id);

}
