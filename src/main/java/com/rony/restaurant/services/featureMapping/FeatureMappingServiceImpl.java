package com.rony.restaurant.services.featureMapping;

import com.rony.restaurant.entity.FeatureMapping;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.FeatureMappingDTO;
import com.rony.restaurant.models.FeatureMappingListResponseDTO;
import com.rony.restaurant.repository.FeatureMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FeatureMappingServiceImpl implements FeatureMappingService {


    @Autowired
    FeatureMappingRepository featureMappingRepository;


    @Override
    public FeatureMappingDTO create(FeatureMappingDTO featureMappingDTO) throws ServiceException {
        FeatureMapping featureMapping = new FeatureMapping(featureMappingDTO);

        this.duplicateCheck(featureMappingDTO, "save");

        return new FeatureMappingDTO(featureMappingRepository.save(featureMapping));
    }

    @Override
    public FeatureMappingDTO update(Long id, FeatureMappingDTO featureMappingDTO) throws ServiceException {
        FeatureMappingDTO _featureMappingDTO = this.findById(id);
        if (_featureMappingDTO == null) throw new NotFoundException();

        this.duplicateCheck(featureMappingDTO, "");

        _featureMappingDTO.setRoleId(featureMappingDTO.getRoleId());
        _featureMappingDTO.setFeatureId(featureMappingDTO.getFeatureId());
        _featureMappingDTO.setStatus(featureMappingDTO.getStatus());

        FeatureMapping featureMapping = new FeatureMapping(_featureMappingDTO);

        return new FeatureMappingDTO(featureMappingRepository.save(featureMapping));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        FeatureMappingDTO featureMappingDTO = this.findById(id);
        if (featureMappingDTO == null) throw new NotFoundException();

        featureMappingRepository.deleteById(id);
        return true;
    }

    @Override
    public FeatureMappingDTO findById(Long id) throws ServiceException {
        Optional<FeatureMapping> featureMappingData = featureMappingRepository.findById(id);
        return new FeatureMappingDTO(featureMappingData.orElse(null));
    }

    @Override
    public Page<FeatureMappingDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<FeatureMappingDTO> findAll() {
        return null;
    }

    private void duplicateCheck(FeatureMappingDTO featureMappingDTO, String type) throws ServiceException {
        FeatureMappingDTO mappingDTO = this.findById(featureMappingDTO.getId());
        if (mappingDTO == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("Mapping exists");
        } else {
            int count = featureMappingRepository.countByRoleAndFeature(mappingDTO.getRoleId(), mappingDTO.getFeatureId());
            if (count >= 1) {
                throw new GeneralException("Mapping exists");
            }
        }
    }

    @Override
    public List<FeatureMappingListResponseDTO> findAllFeatureMapping() {
        return featureMappingRepository.findAllFeatureMapping();
    }
}
