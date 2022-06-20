package com.myhome.services.address;

import com.myhome.models.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAll();

    public void save(Address address);

    public void deleteById(Integer id);

    public void update(Address address);

}
