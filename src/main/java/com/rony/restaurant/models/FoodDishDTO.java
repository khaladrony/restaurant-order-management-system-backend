package com.rony.restaurant.models;


import com.rony.restaurant.entity.FoodCategory;
import com.rony.restaurant.entity.FoodDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDishDTO {

    private Long id;
    private int version;
    private String name;
    private FoodCategory foodCategory;
    private String status;
    private String imageName;
    private String imageType;
    private byte[] imageByte;


    public FoodDishDTO(FoodDish foodDish){
        this.setId(foodDish.getId());
        this.setVersion(foodDish.getVersion());
        this.setName(foodDish.getName());
        this.setFoodCategory(foodDish.getFoodCategory());
        this.setStatus(foodDish.getStatus());
        this.setImageName(foodDish.getImageName());
        this.setImageType(foodDish.getImageType());
        this.setImageByte(foodDish.getImageByte());
    }
}
