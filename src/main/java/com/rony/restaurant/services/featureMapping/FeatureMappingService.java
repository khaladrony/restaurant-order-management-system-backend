package com.rony.restaurant.services.featureMapping;

import com.rony.restaurant.models.FeatureMappingDTO;
import com.rony.restaurant.models.FeatureMappingListResponseDTO;
import com.rony.restaurant.services.AbstractService;

import java.util.List;

public interface FeatureMappingService  extends AbstractService<FeatureMappingDTO> {

    List<FeatureMappingListResponseDTO> findAllFeatureMapping();
}
