package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public Optional<Advertisement> findById(Integer id) {
        return advertisementRepository.findById(id);
    }


}
