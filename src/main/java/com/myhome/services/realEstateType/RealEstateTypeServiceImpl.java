package com.myhome.services.realEstateType;

import com.myhome.models.RealEstateType;
import com.myhome.repository.RealEstateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateTypeServiceImpl implements RealEstateTypeService {

    private final RealEstateTypeRepository realEstateTypeRepository;

    @Override
    public List<RealEstateType> findAll() {
        return realEstateTypeRepository.findAll();
    }

    @Override
    public RealEstateType findById(Integer id) {
        System.out.println(id);
        System.out.println(realEstateTypeRepository.findById(id).get().getName());
        return realEstateTypeRepository.findById(id).get();
    }

}
