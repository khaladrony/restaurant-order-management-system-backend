package com.rony.restaurant.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDishResponse {

    private Long id;
    private Long foodCategoryId;
    private String category;
    private byte[] categoryImageByte;
    private String categoryImageName;
    private String categoryImageType;
    private Long dishId;
    private String dishName;
    private byte[] dishImageByte;
    private String dishImageName;
    private String dishImageType;
    private String status;

}
