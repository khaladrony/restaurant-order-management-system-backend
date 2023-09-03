package com.rony.restaurant.models;


import com.rony.restaurant.entity.FoodDish;
import com.rony.restaurant.entity.Restaurant;
import com.rony.restaurant.entity.RestaurantDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDishDTO {

    private Long id;
    private int version;
    private Restaurant restaurant;
    private FoodDish foodDish;
    private String status;

    public RestaurantDishDTO(RestaurantDish restaurantDish){
        this.setId(restaurantDish.getId());
        this.setVersion(restaurantDish.getVersion());
        this.setRestaurant(restaurantDish.getRestaurant());
        this.setFoodDish(restaurantDish.getFoodDish());
        this.setStatus(restaurantDish.getStatus());
    }
}
