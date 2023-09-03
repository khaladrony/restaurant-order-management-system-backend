package com.rony.restaurant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.restaurant.models.ApiResponse;
import com.rony.restaurant.models.FoodDishDTO;
import com.rony.restaurant.services.foodDish.FoodDishService;
import com.rony.restaurant.services.imageUpload.ImageUploadService;
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
@RequestMapping("/food-dish")
public class FoodDishController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FoodDishService foodDishService;
    @Autowired
    private ImageUploadService imageUploadService;


    /**
     * Create Dish
     *
     * @param foodDishDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> login(@RequestParam(value = "foodDishDTO") String foodDishDTO,
                                        @RequestParam("imageFile") MultipartFile file) {

        ApiResponse response = new ApiResponse(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodDishDTO reqModel = mapper.readValue(foodDishDTO, FoodDishDTO.class);
            reqModel.setImageName(file.getOriginalFilename());
            reqModel.setImageType(file.getContentType());
            reqModel.setImageByte(imageUploadService.compressBytes(file.getBytes()));

            response.setData(foodDishService.create(reqModel));
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Update Dish
     *
     * @param foodDishDTO
     * @return
     */
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam(value = "foodDishDTO") String foodDishDTO,
                                         @RequestParam("imageFile") MultipartFile file) {
        ApiResponse response = new ApiResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodDishDTO reqModel = mapper.readValue(foodDishDTO, FoodDishDTO.class);
            if (reqModel.getId() != null) {
                reqModel.setImageName(file.getOriginalFilename());
                reqModel.setImageType(file.getContentType());
                reqModel.setImageByte(imageUploadService.compressBytes(file.getBytes()));

                response.setData(foodDishService.update(reqModel.getId(), reqModel));
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
     * Delete Dish
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam @Valid Long id) {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(foodDishService.delete(id));
            response.setMessage(messageSource.getMessage("api.delete.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Get Dish list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<Object> getList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(foodDishService.findAll());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
