package com.myhome.services.address;

import com.myhome.models.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAll();

    public Address save(Address address);

}
