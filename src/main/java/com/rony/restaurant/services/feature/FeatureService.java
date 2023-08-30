package com.rony.restaurant.services.feature;

import com.rony.restaurant.entity.Feature;
import com.rony.restaurant.models.FeatureDTO;
import com.rony.restaurant.services.AbstractService;

import java.util.List;

public interface FeatureService extends AbstractService<FeatureDTO> {

    List<Feature> findAllParentFeature();
}
