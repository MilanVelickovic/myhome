package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.models.User;
import com.myhome.utils.AdvertisementForm;

import java.time.Instant;
import java.util.List;

public interface AdvertisementService {

    public List<Advertisement> findAll();

    public Advertisement findById(Integer id);

    public List<Advertisement> findByUser(User user);

    public void save(Advertisement advertisement);

    public Advertisement findByPublishedOn(Instant instant);

    public void createAdvertisement(AdvertisementForm advertisementForm);

    public void deleteById(Integer id);

    public void update(Advertisement advertisement);

    public void updateAdvertisement(AdvertisementForm advertisementForm);

}
