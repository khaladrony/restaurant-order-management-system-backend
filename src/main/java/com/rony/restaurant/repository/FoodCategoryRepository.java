package com.rony.restaurant.repository;

import com.rony.restaurant.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    @Query("select count(category) from FoodCategory category where category.id <> ?1 AND category.category = ?2 ")
    int countByCategory(Long id, String category);
}
