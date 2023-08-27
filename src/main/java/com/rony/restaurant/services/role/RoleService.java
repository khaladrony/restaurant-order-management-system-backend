package com.rony.restaurant.services.role;


import com.rony.restaurant.models.RoleDTO;
import com.rony.restaurant.services.AbstractService;

public interface RoleService extends AbstractService<RoleDTO> {
    RoleDTO findByName(String name);
}
