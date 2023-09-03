package com.rony.restaurant.entity;


import com.rony.restaurant.models.RestaurantDishDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_dish")
public class RestaurantDish extends CommonColumn  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_dish_id")
    private FoodDish foodDish;

    @Column(name = "status")
    private String status;

    public RestaurantDish(RestaurantDishDTO restaurantDishDTO){
        this.setId(restaurantDishDTO.getId());
        this.setVersion(restaurantDishDTO.getVersion());
        this.setRestaurant(restaurantDishDTO.getRestaurant());
        this.setFoodDish(restaurantDishDTO.getFoodDish());
        this.setStatus(restaurantDishDTO.getStatus());
    }
}
