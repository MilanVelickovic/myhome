package com.myhome.services.serviceType;

import com.myhome.models.ServiceType;
import com.myhome.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Override
    public List<ServiceType> getAll() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType findById(Integer id) {
        return serviceTypeRepository.findById(id).get();
    }

}
