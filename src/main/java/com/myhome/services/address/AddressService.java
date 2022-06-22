package com.myhome.services.address;

import com.myhome.models.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAll();

    public Address save(Address address);

    public void deleteById(Integer id);

    public Address update(Address address);

}
