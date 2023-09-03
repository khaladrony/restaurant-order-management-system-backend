package com.rony.restaurant.models;


import com.rony.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private Long id;
    private int version;
    private String name;
    private String address;
    private String phoneNo;
    private String webAddress;
    private String status;
    private String imageName;
    private String imageType;
    private byte[] imageByte;

    public RestaurantDTO(Restaurant restaurant){
        this.setId(restaurant.getId());
        this.setVersion(restaurant.getVersion());
        this.setName(restaurant.getName());
        this.setAddress(restaurant.getAddress());
        this.setPhoneNo(restaurant.getPhoneNo());
        this.setWebAddress(restaurant.getWebAddress());
        this.setStatus(restaurant.getStatus());
        this.setImageName(restaurant.getImageName());
        this.setImageType(restaurant.getImageType());
        this.setImageByte(restaurant.getImageByte());
    }
}
