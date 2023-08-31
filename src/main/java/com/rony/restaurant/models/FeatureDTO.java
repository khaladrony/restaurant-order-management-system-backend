package com.rony.restaurant.models;


import com.rony.restaurant.entity.Feature;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDTO {

    private Long id;
    private int version;
    private String name;
    private String path;
    private String icon;
    private String type;
    private boolean isCollapsed;
    private Long parentFeatureId;
    private String status;
    private String parentFeatureName;

    public FeatureDTO(Feature feature){
        this.setId(feature.getId());
        this.setVersion(feature.getVersion());
        this.setName(feature.getName());
        this.setPath(feature.getPath());
        this.setIcon(feature.getIcon());
        this.setType(feature.getType());
        this.setCollapsed(feature.isCollapsed());
        this.setParentFeatureId(feature.getParentFeatureId());
        this.setStatus(feature.getStatus());
        this.setParentFeatureName(feature.getParentFeatureName());
    }
}
