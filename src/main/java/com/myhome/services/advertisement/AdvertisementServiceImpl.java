package com.myhome.services.advertisement;

import com.myhome.models.Advertisement;
import com.myhome.models.User;
import com.myhome.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement findById(Integer id) {
        return advertisementRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findByUser(User user) {
        return advertisementRepository.findByUser(user).get();
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement findByPublishedOn(Instant instant) {
        return advertisementRepository.findByPublishedOn(instant).get();
    }


}
