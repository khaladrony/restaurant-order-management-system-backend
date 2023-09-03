package com.rony.restaurant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.restaurant.entity.RestaurantDish;
import com.rony.restaurant.models.ApiResponse;
import com.rony.restaurant.models.RestaurantDishDTO;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
import com.rony.restaurant.services.restaurant.RestaurantService;
import com.rony.restaurant.services.restaurantDish.RestaurantDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/restaurant-dish")
public class RestaurantDishController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private RestaurantDishService restaurantDishService;
    @Autowired
    private ImageUploadService imageUploadService;


    /**
     * Create Restaurant Dish
     *
     * @param restaurantDishDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> login(@RequestParam(value = "restaurantDishDTO") String restaurantDishDTO) {

        ApiResponse response = new ApiResponse(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            RestaurantDishDTO reqModel = mapper.readValue(restaurantDishDTO, RestaurantDishDTO.class);
            response.setData(restaurantDishService.create(reqModel));
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Update Restaurant Dish
     *
     * @param restaurantDishDTO
     * @return
     */
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam(value = "restaurantDishDTO") String restaurantDishDTO) {
        ApiResponse response = new ApiResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            RestaurantDishDTO reqModel = mapper.readValue(restaurantDishDTO, RestaurantDishDTO.class);
            if (reqModel.getId() != null) {
                response.setData(restaurantDishService.update(reqModel.getId(), reqModel));
                response.setMessage(messageSource.getMessage("api.update.success", null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setMessage("Food category ID not found!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Delete Restaurant
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam @Valid Long id) {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(restaurantDishService.delete(id));
            response.setMessage(messageSource.getMessage("api.delete.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Get Restaurant list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<Object> getList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(restaurantDishService.findAll());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
