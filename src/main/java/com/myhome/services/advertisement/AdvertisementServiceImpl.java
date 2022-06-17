package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    public Advertisement findById(Integer id) {
        return advertisementRepository.getAdvertisementById(id);
    }

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

}
