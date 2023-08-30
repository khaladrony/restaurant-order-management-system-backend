package com.rony.restaurant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.restaurant.models.ApiResponse;
import com.rony.restaurant.models.FeatureDTO;
import com.rony.restaurant.services.feature.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    FeatureService featureService;


    /**
     * Create Feature
     *
     * @param featureDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> login(@RequestParam(value = "featureDTO") String featureDTO) {

        ApiResponse response = new ApiResponse(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            FeatureDTO reqModel = mapper.readValue(featureDTO, FeatureDTO.class);
            response.setData(featureService.create(reqModel));
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Update Feature
     *
     * @param featureDTO
     * @return
     */
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam(value = "featureDTO") String featureDTO) {
        ApiResponse response = new ApiResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            FeatureDTO reqModel = mapper.readValue(featureDTO, FeatureDTO.class);
            if (reqModel.getId() != null) {
                response.setData(featureService.update(reqModel.getId(), reqModel));
                response.setMessage(messageSource.getMessage("api.update.success", null, null));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setMessage("Project ID not found!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Delete Feature
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam @Valid Long id) {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(featureService.delete(id));
            response.setMessage(messageSource.getMessage("api.delete.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Get Feature list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<Object> getList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(featureService.findAll());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Get all parent feature list
     *
     * @return
     */
    @RequestMapping(value = "/parent-features", method = RequestMethod.POST)
    public ResponseEntity<Object> getParentFeatureList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(featureService.findAllParentFeature());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
