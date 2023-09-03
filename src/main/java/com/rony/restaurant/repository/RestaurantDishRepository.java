package com.rony.restaurant.repository;

import com.rony.restaurant.entity.RestaurantDish;
import com.rony.restaurant.models.RestaurantDishResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantDishRepository extends JpaRepository<RestaurantDish, Long> {


}
