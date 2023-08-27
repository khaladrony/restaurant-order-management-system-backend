package com.rony.restaurant.models;


import com.rony.restaurant.entity.Role;
import com.rony.restaurant.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private int version;
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private String address;
    private String status;
    private Role role;

    public UserDTO(User user){
        this.setId(user.getId());
        this.setVersion(user.getVersion());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setPhoneNo(user.getPhoneNo());
        this.setAddress(user.getAddress());
        this.setStatus(user.getStatus());
        this.setRole(user.getRole());
    }

}
