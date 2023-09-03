package com.rony.restaurant.services.foodDish;

import com.rony.restaurant.entity.FoodDish;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.FoodCategoryDTO;
import com.rony.restaurant.models.FoodDishDTO;
import com.rony.restaurant.repository.FoodCategoryRepository;
import com.rony.restaurant.repository.FoodDishRepository;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FoodDishServiceImpl implements FoodDishService {

    @Autowired
    private FoodDishRepository foodDishRepository;
    @Autowired
    private ImageUploadService imageUploadService;


    @Override
    public FoodDishDTO create(FoodDishDTO foodDishDTO) throws ServiceException {
        FoodDish foodDish = new FoodDish(foodDishDTO);

        this.duplicateCheck(foodDishDTO, "save");
        return new FoodDishDTO(foodDishRepository.save(foodDish));
    }

    @Override
    public FoodDishDTO update(Long id, FoodDishDTO foodDishDTO) throws ServiceException {
        FoodDishDTO _foodDishDTO = this.findById(id);
        if (_foodDishDTO == null) throw new NotFoundException();

        this.duplicateCheck(foodDishDTO, "");

        _foodDishDTO.setName(foodDishDTO.getName());
        _foodDishDTO.setFoodCategory(foodDishDTO.getFoodCategory());
        _foodDishDTO.setStatus(foodDishDTO.getStatus());
        _foodDishDTO.setImageName(foodDishDTO.getImageName());
        _foodDishDTO.setImageType(foodDishDTO.getImageType());
        _foodDishDTO.setImageByte(foodDishDTO.getImageByte());
        FoodDish foodDish = new FoodDish(_foodDishDTO);

        return new FoodDishDTO(foodDishRepository.save(foodDish));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        FoodDishDTO foodDishDTO = this.findById(id);
        if (foodDishDTO == null) throw new NotFoundException();

        foodDishRepository.deleteById(id);
        return true;
    }

    @Override
    public FoodDishDTO findById(Long id) throws ServiceException {
        Optional<FoodDish> foodDishData = foodDishRepository.findById(id);
        return new FoodDishDTO(foodDishData.orElse(null));
    }

    @Override
    public Page<FoodDishDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<FoodDishDTO> findAll() {
        List<FoodDishDTO> foodDishDTOList = new ArrayList<>();
        List<FoodDish> foodDishList = foodDishRepository.findAll();
        for (FoodDish foodDish : foodDishList) {
            FoodDishDTO foodDishDTO = new FoodDishDTO(foodDish);
            foodDishDTO.setImageByte(imageDecompress(foodDishDTO.getImageByte()));
            foodDishDTOList.add(foodDishDTO);
        }
        return foodDishDTOList;
    }

    private void duplicateCheck(FoodDishDTO foodDishDTO, String type) throws ServiceException {
        FoodDishDTO foodDishDTOData = foodDishDTO.getId() == null ? null : this.findById(foodDishDTO.getId());
        if (foodDishDTOData == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("The food category exists");
        } else {
//            int count = foodCategoryRepository.countByCategory(foodCategoryDTO.getId(), foodCategoryDTO.getCategory());
//            if (count >= 1) {
//                throw new GeneralException("The food category exists");
//            }
        }

    }

    private byte[] imageDecompress(byte[] imageByte){

        return imageUploadService.decompressBytes(imageByte);

    }
}
