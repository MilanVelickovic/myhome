package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.models.RealEstate;
import com.myhome.models.User;
import com.myhome.utils.forms.AdvertisementForm;

import java.time.Instant;
import java.util.List;

public interface AdvertisementService {

    public List<Advertisement> findAll();

    public Advertisement findById(Integer id);

    public List<Advertisement> findByUser(User user);

    public Advertisement save(Advertisement advertisement);

    public Advertisement findByPublishedOn(Instant instant);

    public void createAdvertisement(AdvertisementForm advertisementForm);

    public void deleteAdvertisementById(Integer id);

    public void deleteById(Integer id);

    public Advertisement update(Advertisement advertisement);

    public Advertisement update(AdvertisementForm advertisementForm);

    public void deleteAllByUser(User user);

}
