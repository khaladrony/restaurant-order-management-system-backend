package com.rony.restaurant.models;

import com.rony.restaurant.entity.Feature;
import com.rony.restaurant.entity.FeatureMapping;
import com.rony.restaurant.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeatureMappingDTO {

    private Long id;
    private Long roleId;
    private Long featureId;
    private String status;

    public FeatureMappingDTO(FeatureMapping featureMapping){
        this.setId(featureMapping.getId());
        this.setRoleId(featureMapping.getRoleId());
        this.setFeatureId(featureMapping.getFeatureId());
        this.setStatus(featureMapping.getStatus());
    }

}
