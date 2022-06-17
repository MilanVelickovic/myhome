package com.myhome.services.realEstateType;

import com.myhome.models.RealEstateType;
import com.myhome.repository.RealEstateTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RealEstateTypeServiceImpl implements RealEstateTypeService {

    private RealEstateTypeRepository realEstateTypeRepository;

    @Override
    public List<RealEstateType> getAll() {
        return realEstateTypeRepository.findAll();
    }

}
