package com.rony.restaurant.services.foodCategory;

import com.rony.restaurant.entity.FoodCategory;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.FoodCategoryDTO;
import com.rony.restaurant.repository.FoodCategoryRepository;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;
    @Autowired
    private ImageUploadService imageUploadService;

    @Override
    public FoodCategoryDTO create(FoodCategoryDTO foodCategoryDTO) throws ServiceException {
        FoodCategory foodCategory = new FoodCategory(foodCategoryDTO);

        this.duplicateCheck(foodCategoryDTO, "save");
        return new FoodCategoryDTO(foodCategoryRepository.save(foodCategory));

    }

    @Override
    public FoodCategoryDTO update(Long id, FoodCategoryDTO foodCategoryDTO) throws ServiceException {
        FoodCategoryDTO _foodCategoryDTO = this.findById(id);
        if (_foodCategoryDTO == null) throw new NotFoundException();

        this.duplicateCheck(foodCategoryDTO, "");

        _foodCategoryDTO.setCategory(foodCategoryDTO.getCategory());
        _foodCategoryDTO.setStatus(foodCategoryDTO.getStatus());
        _foodCategoryDTO.setImageName(foodCategoryDTO.getImageName());
        _foodCategoryDTO.setImageType(foodCategoryDTO.getImageType());
        _foodCategoryDTO.setImageByte(foodCategoryDTO.getImageByte());
        FoodCategory foodCategory = new FoodCategory(_foodCategoryDTO);

        return new FoodCategoryDTO(foodCategoryRepository.save(foodCategory));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        FoodCategoryDTO foodCategoryDTO = this.findById(id);
        if (foodCategoryDTO == null) throw new NotFoundException();

        foodCategoryRepository.deleteById(id);
        return true;
    }

    @Override
    public FoodCategoryDTO findById(Long id) throws ServiceException {
        Optional<FoodCategory> foodCategoryData = foodCategoryRepository.findById(id);
        return new FoodCategoryDTO(foodCategoryData.orElse(null));
    }

    @Override
    public Page<FoodCategoryDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<FoodCategoryDTO> findAll() {
        List<FoodCategoryDTO> foodCategoryDTOList = new ArrayList<>();
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        for (FoodCategory foodCategory : foodCategoryList) {
            FoodCategoryDTO foodCategoryDTO = new FoodCategoryDTO(foodCategory);
            foodCategoryDTO.setImageByte(imageDecompress(foodCategoryDTO.getImageByte()));
            foodCategoryDTOList.add(foodCategoryDTO);
        }
        return foodCategoryDTOList;
    }

    private void duplicateCheck(FoodCategoryDTO foodCategoryDTO, String type) throws ServiceException {
        FoodCategoryDTO foodCategoryDTOData = foodCategoryDTO.getId() == null ? null : this.findById(foodCategoryDTO.getId());
        if (foodCategoryDTOData == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("The food category exists");
        } else {
            int count = foodCategoryRepository.countByCategory(foodCategoryDTO.getId(), foodCategoryDTO.getCategory());
            if (count >= 1) {
                throw new GeneralException("The food category exists");
            }
        }

    }

    private byte[] imageDecompress(byte[] imageByte){

        return imageUploadService.decompressBytes(imageByte);

    }
}
