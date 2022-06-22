package com.myhome.services.realEstate;

import com.myhome.models.RealEstate;

import java.util.List;

public interface RealEstateService {

    public List<RealEstate> getAll();

    public RealEstate save(RealEstate realEstate);

    public void deleteById(Integer id);

    public RealEstate update(RealEstate realEstate);

}
