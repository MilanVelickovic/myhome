package com.myhome.services.role;

import com.myhome.models.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAll();

    public Role findById(Integer id);

}
