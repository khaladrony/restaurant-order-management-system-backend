package com.rony.restaurant.services.restaurant;

import com.rony.restaurant.entity.Restaurant;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.FoodDishDTO;
import com.rony.restaurant.models.RestaurantDTO;
import com.rony.restaurant.repository.RestaurantRepository;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDTO create(RestaurantDTO restaurantDTO) throws ServiceException {
        Restaurant restaurant = new Restaurant(restaurantDTO);

        this.duplicateCheck(restaurantDTO, "save");
        return new RestaurantDTO(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDTO update(Long id, RestaurantDTO restaurantDTO) throws ServiceException {
        RestaurantDTO _restaurantDTO = this.findById(id);
        if (_restaurantDTO == null) throw new NotFoundException();

        this.duplicateCheck(restaurantDTO, "");

        _restaurantDTO.setName(restaurantDTO.getName());
        _restaurantDTO.setAddress(restaurantDTO.getAddress());
        _restaurantDTO.setWebAddress(restaurantDTO.getWebAddress());
        _restaurantDTO.setPhoneNo(restaurantDTO.getPhoneNo());
        _restaurantDTO.setStatus(restaurantDTO.getStatus());
        _restaurantDTO.setImageName(restaurantDTO.getImageName());
        _restaurantDTO.setImageType(restaurantDTO.getImageType());
        _restaurantDTO.setImageByte(restaurantDTO.getImageByte());
        Restaurant restaurant = new Restaurant(_restaurantDTO);

        return new RestaurantDTO(restaurantRepository.save(restaurant));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        RestaurantDTO restaurantDTO = this.findById(id);
        if (restaurantDTO == null) throw new NotFoundException();

        restaurantRepository.deleteById(id);
        return true;
    }

    @Override
    public RestaurantDTO findById(Long id) throws ServiceException {
        Optional<Restaurant> restaurantData = restaurantRepository.findById(id);
        return new RestaurantDTO(restaurantData.orElse(null));
    }

    @Override
    public Page<RestaurantDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<RestaurantDTO> findAll() {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurantList) {
            RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant);
            restaurantDTO.setImageByte(imageDecompress(restaurantDTO.getImageByte()));
            restaurantDTOList.add(restaurantDTO);
        }
        return restaurantDTOList;
    }

    private void duplicateCheck(RestaurantDTO restaurantDTO, String type) throws ServiceException {
        RestaurantDTO restaurantDTOData = restaurantDTO.getId() == null ? null : this.findById(restaurantDTO.getId());
        if (restaurantDTOData == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("The restaurant exists");
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
