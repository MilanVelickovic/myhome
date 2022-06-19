package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.models.User;

import java.time.Instant;
import java.util.List;

public interface AdvertisementService {

    public List<Advertisement> findAll();

    public Advertisement findById(Integer id);

    public List<Advertisement> findByUser(User user);

    public Advertisement save(Advertisement advertisement);

    public Advertisement findByPublishedOn(Instant instant);

}
