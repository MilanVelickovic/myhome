package com.myhome.services.role;

import com.myhome.models.Role;
import com.myhome.repository.RoleRepository;

import java.util.List;

public class RoleServiceImpl implements RoleServices {

    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
