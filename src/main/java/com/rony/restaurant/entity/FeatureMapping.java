package com.rony.restaurant.entity;


import com.rony.restaurant.models.FeatureMappingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "features_mapping")
public class FeatureMapping extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "feature_id")
    private Long featureId;

    @Column(name = "status")
    private String status;

    public FeatureMapping(FeatureMappingDTO featureMappingDTO){
        this.setId(featureMappingDTO.getId());
        this.setRoleId(featureMappingDTO.getRoleId());
        this.setFeatureId(featureMappingDTO.getFeatureId());
        this.setStatus(featureMappingDTO.getStatus());
    }
}
