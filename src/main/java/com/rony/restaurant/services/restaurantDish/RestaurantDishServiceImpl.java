package com.rony.restaurant.services.restaurantDish;

import com.rony.restaurant.entity.RestaurantDish;
import com.rony.restaurant.exception.GeneralException;
import com.rony.restaurant.exception.NotFoundException;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.RestaurantDishDTO;
import com.rony.restaurant.repository.RestaurantDishRepository;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantDishServiceImpl implements RestaurantDishService {

    @Autowired
    private RestaurantDishRepository restaurantDishRepository;
    @Autowired
    private ImageUploadService imageUploadService;
//    @Autowired
//    private RestaurantDishResponseRepository restaurantDishResponseRepository;

    @Override
    public RestaurantDishDTO create(RestaurantDishDTO restaurantDishDTO) throws ServiceException {
        RestaurantDish restaurantDish = new RestaurantDish(restaurantDishDTO);

        this.duplicateCheck(restaurantDishDTO, "save");
        return new RestaurantDishDTO(restaurantDishRepository.save(restaurantDish));
    }

    @Override
    public RestaurantDishDTO update(Long id, RestaurantDishDTO restaurantDishDTO) throws ServiceException {
        RestaurantDishDTO _restaurantDishDTO = this.findById(id);
        if (_restaurantDishDTO == null) throw new NotFoundException();

        this.duplicateCheck(restaurantDishDTO, "");

        _restaurantDishDTO.setRestaurant(restaurantDishDTO.getRestaurant());
        _restaurantDishDTO.setFoodDish(restaurantDishDTO.getFoodDish());
        _restaurantDishDTO.setStatus(restaurantDishDTO.getStatus());
        RestaurantDish restaurantDish = new RestaurantDish(_restaurantDishDTO);

        return new RestaurantDishDTO(restaurantDishRepository.save(restaurantDish));
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        RestaurantDishDTO restaurantDishDTO = this.findById(id);
        if (restaurantDishDTO == null) throw new NotFoundException();

        restaurantDishRepository.deleteById(id);
        return true;
    }

    @Override
    public RestaurantDishDTO findById(Long id) throws ServiceException {
        Optional<RestaurantDish> restaurantDishData = restaurantDishRepository.findById(id);
        return new RestaurantDishDTO(restaurantDishData.orElse(null));
    }

    @Override
    public Page<RestaurantDishDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<RestaurantDishDTO> findAll() {
        List<RestaurantDishDTO> restaurantDishDTOList = new ArrayList<>();
        List<RestaurantDish> restaurantDishList = restaurantDishRepository.findAll();
        for (RestaurantDish restaurantDish : restaurantDishList) {
            RestaurantDishDTO restaurantDishDTO = new RestaurantDishDTO(restaurantDish);
            restaurantDishDTO.getRestaurant().setImageByte(imageDecompress(restaurantDishDTO.getRestaurant().getImageByte()));
            restaurantDishDTO.getFoodDish().setImageByte(imageDecompress(restaurantDishDTO.getFoodDish().getImageByte()));

            restaurantDishDTOList.add(restaurantDishDTO);
        }
        return restaurantDishDTOList;
    }

    private void duplicateCheck(RestaurantDishDTO restaurantDishDTO, String type) throws ServiceException {
        RestaurantDishDTO restaurantDishDTOData = restaurantDishDTO.getId() == null ? null : this.findById(restaurantDishDTO.getId());
        if (restaurantDishDTOData == null) return;

        if (type.equalsIgnoreCase("save")) {
            throw new GeneralException("The Restaurant Dish exists");
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
