package com.rony.restaurant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.restaurant.models.ApiResponse;
import com.rony.restaurant.models.FoodCategoryDTO;
import com.rony.restaurant.services.foodCategory.FoodCategoryService;
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
@RequestMapping("/food-category")
public class FoodCategoryController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FoodCategoryService foodCategoryService;
    @Autowired
    private ImageUploadService imageUploadService;


    /**
     * Create Food Category
     *
     * @param foodCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> login(@RequestParam(value = "foodCategoryDTO") String foodCategoryDTO,
                                        @RequestParam("imageFile") MultipartFile file) {

        ApiResponse response = new ApiResponse(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodCategoryDTO reqModel = mapper.readValue(foodCategoryDTO, FoodCategoryDTO.class);
            reqModel.setImageName(file.getOriginalFilename());
            reqModel.setImageType(file.getContentType());
            reqModel.setImageByte(imageUploadService.compressBytes(file.getBytes()));

            response.setData(foodCategoryService.create(reqModel));
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Update Food Category
     *
     * @param foodCategoryDTO
     * @return
     */
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam(value = "foodCategoryDTO") String foodCategoryDTO,
                                         @RequestParam("imageFile") MultipartFile file) {
        ApiResponse response = new ApiResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            FoodCategoryDTO reqModel = mapper.readValue(foodCategoryDTO, FoodCategoryDTO.class);
            if (reqModel.getId() != null) {
                reqModel.setImageName(file.getOriginalFilename());
                reqModel.setImageType(file.getContentType());
                reqModel.setImageByte(imageUploadService.compressBytes(file.getBytes()));

                response.setData(foodCategoryService.update(reqModel.getId(), reqModel));
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
     * Delete Food Category
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam @Valid Long id) {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(foodCategoryService.delete(id));
            response.setMessage(messageSource.getMessage("api.delete.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Get Food Category list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<Object> getList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(foodCategoryService.findAll());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
