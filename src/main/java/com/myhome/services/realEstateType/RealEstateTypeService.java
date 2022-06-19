package com.myhome.services.realEstateType;

import com.myhome.models.RealEstateType;

import java.util.List;

public interface RealEstateTypeService {

    public List<RealEstateType> findAll();

    public RealEstateType findById(Integer id);

}
