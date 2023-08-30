package com.rony.restaurant.services.feature;

import com.rony.restaurant.entity.Feature;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.FeatureDTO;
import com.rony.restaurant.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureRepository featureRepository;


    @Override
    public FeatureDTO create(FeatureDTO featureDTO) throws ServiceException {
        Feature feature = new Feature(featureDTO);

        this.setValue(feature);

        this.duplicateCheck(featureDTO, "save");

        return new FeatureDTO(featureRepository.save(feature));
    }

    @Override
    public FeatureDTO update(Long id, FeatureDTO featureDTO) throws ServiceException {
        FeatureDTO _featureDTO = this.findById(id);
        if (_featureDTO == null) throw new NotFoundException();

        this.duplicateCheck(featureDTO, "");

        _featureDTO.setName(featureDTO.getName());
        _featureDTO.setPath(featureDTO.getPath());
        _featureDTO.setIcon(featureDTO.getIcon());
        _featureDTO.setType(featureDTO.getType());
        _featureDTO.setCollapsed(featureDTO.isCollapsed());
        _featureDTO.setParentFeatureId(featureDTO.getParentFeatureId());
        _featureDTO.setParentFeatureName(featureDTO.getParentFeatureName());
        _featureDTO.setStatus(featureDTO.getStatus());

        Feature feature = new Feature(_featureDTO);
        this.setValue(feature);

        return new FeatureDTO(featureRepository.save(feature));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        FeatureDTO featureDTO = this.findById(id);
        if (featureDTO == null) throw new NotFoundException();

        featureRepository.deleteById(id);
        return true;
    }

    @Override
    public FeatureDTO findById(Long id) throws ServiceException {
        Optional<Feature> featureData = featureRepository.findById(id);
        return new FeatureDTO(featureData.orElse(null));
    }

    @Override
    public Page<FeatureDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<FeatureDTO> findAll() {
        List<FeatureDTO> featureDTOS = new ArrayList<>();
        List<Feature> featureList = featureRepository.findAll();
        for (Feature feature : featureList) {
            FeatureDTO featureDTO = new FeatureDTO(feature);
            featureDTOS.add(featureDTO);
        }
        return featureDTOS;
    }


    private void duplicateCheck(FeatureDTO featureDTO, String type) throws ServiceException {
//        FeatureDTO featureDTOObj = this.findByName(roleDTO.getName());
//        if (roleDTOObj == null) return;
//
//        if (type.equalsIgnoreCase("save")
//                && this.findByName(roleDTO.getName()) != null) {
//            throw new GeneralException("The role exists");
//        } else {
//            int count = roleRepository.countByName(roleDTO.getId(), roleDTO.getName());
//            if (count >= 1) {
//                throw new GeneralException("The role exists");
//            }
//        }

    }

    @Override
    public List<Feature> findAllParentFeature() {
        return featureRepository.findAllByParentFeatureIdIsNull();
    }

    public void setValue(Feature feature){
        if (feature.getParentFeatureId() == null) {
            feature.setCollapsed(true);
            feature.setType("submenu");
        } else {
            feature.setType("link");
        }
    }
}
