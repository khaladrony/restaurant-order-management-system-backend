package com.rony.restaurant.repository;

import com.rony.restaurant.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    List<Feature> findAllByParentFeatureIdIsNull();

    @Query("select count(u) from User u where u.id <> ?1 AND u.username = ?2 ")
    List<Feature> findAllFeatureByParentFeatureName();
}
