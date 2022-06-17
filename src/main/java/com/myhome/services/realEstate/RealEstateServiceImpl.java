package com.myhome.services.realEstate;

import com.myhome.models.RealEstate;
import com.myhome.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {

    private RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> getAll() {
        return realEstateRepository.findAll();
    }
}
