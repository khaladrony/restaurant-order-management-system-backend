package com.rony.restaurant.repository;

import com.rony.restaurant.entity.FeatureMapping;
import com.rony.restaurant.models.FeatureMappingListResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureMappingRepository extends JpaRepository<FeatureMapping, Long> {

    @Query(value = "select " +
            "features_map.id, roles.id 'roleId', roles.name 'rolesName', " +
            "features.id 'featureId', features.name 'featuresName', features_map.status " +
            "from features_mapping features_map " +
            "inner join roles as roles on features_map.role_id = roles.id " +
            "inner join features as features on features_map.feature_id = features.id ", nativeQuery = true)
    List<FeatureMappingListResponseDTO> findAllFeatureMapping();

    @Query("select count(map) from FeatureMapping map where map.roleId = ?1 AND map.featureId = ?2 ")
    int countByRoleAndFeature(Long roleId, Long featureId);
}
