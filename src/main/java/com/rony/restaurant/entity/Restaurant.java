package com.rony.restaurant.entity;


import com.rony.restaurant.models.RestaurantDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant extends CommonColumn  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "status")
    private String status;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_byte", length = 1000)
    private byte[] imageByte;

    public Restaurant(RestaurantDTO restaurantDTO){
        this.setId(restaurantDTO.getId());
        this.setVersion(restaurantDTO.getVersion());
        this.setName(restaurantDTO.getName());
        this.setAddress(restaurantDTO.getAddress());
        this.setPhoneNo(restaurantDTO.getPhoneNo());
        this.setWebAddress(restaurantDTO.getWebAddress());
        this.setStatus(restaurantDTO.getStatus());
        this.setImageName(restaurantDTO.getImageName());
        this.setImageType(restaurantDTO.getImageType());
        this.setImageByte(restaurantDTO.getImageByte());
    }
}
