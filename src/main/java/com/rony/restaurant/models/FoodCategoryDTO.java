package com.rony.restaurant.models;


import com.rony.restaurant.entity.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryDTO {

    private Long id;
    private int version;
    private String category;
    private String status;
    private String imageName;
    private String imageType;
    private byte[] imageByte;

    public FoodCategoryDTO(FoodCategory foodCategory) {
        this.setId(foodCategory.getId());
        this.setVersion(foodCategory.getVersion());
        this.setCategory(foodCategory.getCategory());
        this.setStatus(foodCategory.getStatus());
        this.setImageName(foodCategory.getImageName());
        this.setImageType(foodCategory.getImageType());
        this.setImageByte(foodCategory.getImageByte());
    }
}
