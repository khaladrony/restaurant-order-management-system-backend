package com.rony.restaurant.models;


import com.rony.restaurant.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {

    private Long id;
    private String name;
    private String description;
    private String status;
    private int version;

    public RoleDTO(Role role){
        this.setId(role.getId());
        this.setName(role.getName());
        this.setDescription(role.getDescription());
        this.setStatus(role.getStatus());
        this.setVersion(role.getVersion());
    }
}
