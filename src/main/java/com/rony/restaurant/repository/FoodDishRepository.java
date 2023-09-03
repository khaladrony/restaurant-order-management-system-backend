package com.rony.restaurant.repository;

import com.rony.restaurant.entity.FoodDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDishRepository extends JpaRepository<FoodDish, Long> {
}
