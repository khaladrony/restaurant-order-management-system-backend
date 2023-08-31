package com.rony.restaurant.entity;


import com.rony.restaurant.models.FeatureDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "features")
public class Feature extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "icon")
    private String icon;

    @Column(name = "type")
    private String type;        //link,submenu

    @Column(name = "is_collapsed", columnDefinition = "boolean default false")
    private boolean isCollapsed;

    @Column(name = "parent_feature_id")
    private Long parentFeatureId;

    @Column(name = "parent_feature_name")
    private String parentFeatureName;

    @Column(name = "status")
    private String status;


    public Feature(FeatureDTO featureDTO){
        this.setId(featureDTO.getId());
        this.setVersion(featureDTO.getVersion());
        this.setName(featureDTO.getName());
        this.setPath(featureDTO.getPath());
        this.setIcon(featureDTO.getIcon());
        this.setType(featureDTO.getType());
        this.setCollapsed(featureDTO.isCollapsed());
        this.setParentFeatureId(featureDTO.getParentFeatureId());
        this.setStatus(featureDTO.getStatus());
        this.setParentFeatureName(featureDTO.getParentFeatureName());
    }
}
