package com.myhome.services.realEstate;

import com.myhome.models.RealEstate;
import com.myhome.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {

    private final RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.findAll();
    }

    @Override
    public RealEstate save(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }
}
