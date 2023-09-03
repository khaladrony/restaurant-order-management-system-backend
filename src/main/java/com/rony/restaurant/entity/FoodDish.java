package com.rony.restaurant.entity;


import com.rony.restaurant.models.FoodDishDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_dish")
public class FoodDish extends CommonColumn  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @Column(name = "status")
    private String status;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_byte", length = 1000)
    private byte[] imageByte;

    public FoodDish(FoodDishDTO foodDishDTO){
        this.setId(foodDishDTO.getId());
        this.setVersion(foodDishDTO.getVersion());
        this.setName(foodDishDTO.getName());
        this.setFoodCategory(foodDishDTO.getFoodCategory());
        this.setStatus(foodDishDTO.getStatus());
        this.setImageName(foodDishDTO.getImageName());
        this.setImageType(foodDishDTO.getImageType());
        this.setImageByte(foodDishDTO.getImageByte());
    }
}
