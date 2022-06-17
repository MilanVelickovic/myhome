package com.myhome.services.serviceType;

import com.myhome.models.ServiceType;
import com.myhome.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public List<ServiceType> getAll() {
        return serviceTypeRepository.findAll();
    }

}
