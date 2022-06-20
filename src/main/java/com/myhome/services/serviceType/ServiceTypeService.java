package com.myhome.services.serviceType;

import com.myhome.models.ServiceType;

import java.util.List;

public interface ServiceTypeService {

    public List<ServiceType> findAll();

    public ServiceType findById(Integer id);

}
