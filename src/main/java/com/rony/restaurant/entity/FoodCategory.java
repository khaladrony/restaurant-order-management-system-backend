package com.rony.restaurant.entity;


import com.rony.restaurant.models.FoodCategoryDTO;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_category")
public class FoodCategory extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "status")
    private String status;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_byte", length = 1000)
    private byte[] imageByte;

    public FoodCategory(FoodCategoryDTO foodCategoryDTO) {
        this.setId(foodCategoryDTO.getId());
        this.setVersion(foodCategoryDTO.getVersion());
        this.setCategory(foodCategoryDTO.getCategory());
        this.setStatus(foodCategoryDTO.getStatus());
        this.setImageName(foodCategoryDTO.getImageName());
        this.setImageType(foodCategoryDTO.getImageType());
        this.setImageByte(foodCategoryDTO.getImageByte());
    }
}
