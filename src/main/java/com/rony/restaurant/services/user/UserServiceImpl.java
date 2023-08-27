package com.rony.restaurant.services.user;


import com.rony.restaurant.entity.User;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.UserDTO;
import com.rony.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(UserDTO userDTO) throws ServiceException {
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.duplicateCheck(userDTO, "save");
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) throws ServiceException {
        UserDTO _UserDTO = this.findById(id);
        if (_UserDTO == null) throw new NotFoundException();

        this.duplicateCheck(userDTO, "");

        _UserDTO.setUsername(userDTO.getUsername());
        _UserDTO.setEmail(userDTO.getEmail());
        _UserDTO.setPhoneNo(userDTO.getPhoneNo());
        _UserDTO.setAddress(userDTO.getAddress());
        _UserDTO.setRole(userDTO.getRole());
        _UserDTO.setStatus(userDTO.getStatus());
        User user = new User(_UserDTO);

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        UserDTO userDTO = this.findById(id);
        if (userDTO == null) throw new NotFoundException();

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userData = userRepository.findById(id);
        return new UserDTO(userData.orElse(null));
    }

    @Override
    public UserDTO findByUsername(String username) {
        User userData = userRepository.findByUsername(username);
        if (userData != null) {
            return new UserDTO(userData);
        }
        return null;
    }

    @Override
    public Page<UserDTO> findAll(int page, int size) {
        List<UserDTO> userDTOS = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(paging);

        Iterator var4 = users.iterator();
        while (var4.hasNext()) {
            User user = (User) var4.next();
//            user.setPassword(passwordEncoder.);
            UserDTO userDTO = new UserDTO(user);
            userDTOS.add(userDTO);
        }

        return (Page<UserDTO>) userDTOS;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    private void duplicateCheck(UserDTO userDTO, String type) throws ServiceException {
        UserDTO userDTOObj = this.findByUsername(userDTO.getUsername());
        if (userDTOObj == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("The user exists");
        } else {
            int count = userRepository.countByUsername(userDTO.getId(), userDTO.getUsername());
            if (count >= 1) {
                throw new GeneralException("The user exists");
            }
        }

    }
}
