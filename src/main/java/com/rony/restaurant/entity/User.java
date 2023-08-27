package com.rony.restaurant.entity;


import com.rony.restaurant.models.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 150, nullable = false)
    private String username;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "phone_no", length = 11)
    private String phoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public User(UserDTO userDTO){
        this.setId(userDTO.getId());
        this.setVersion(userDTO.getVersion());
        this.setUsername(userDTO.getUsername());
        this.setPassword(userDTO.getPassword());
        this.setEmail(userDTO.getEmail());
        this.setPhoneNo(userDTO.getPhoneNo());
        this.setAddress(userDTO.getAddress());
        this.setStatus(userDTO.getStatus());
        this.setRole(userDTO.getRole());

    }
}
