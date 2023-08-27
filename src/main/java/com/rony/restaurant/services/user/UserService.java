package com.rony.restaurant.services.user;

import com.rony.restaurant.models.UserDTO;
import com.rony.restaurant.services.AbstractService;

public interface UserService extends AbstractService<UserDTO> {

    UserDTO findByUsername(String username);
}
