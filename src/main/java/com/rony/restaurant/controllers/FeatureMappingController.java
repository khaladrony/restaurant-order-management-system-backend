package com.rony.restaurant.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rony.restaurant.models.ApiResponse;
import com.rony.restaurant.models.FeatureMappingDTO;
import com.rony.restaurant.services.featureMapping.FeatureMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/features-mapping")
public class FeatureMappingController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FeatureMappingService featureMappingService;


    /**
     * Create Feature Mapping
     *
     * @param featureMappingDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> login(@RequestParam(value = "featureMappingDTO") String featureMappingDTO) {

        ApiResponse response = new ApiResponse(false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            FeatureMappingDTO reqModel = mapper.readValue(featureMappingDTO, FeatureMappingDTO.class);
            response.setData(featureMappingService.create(reqModel));
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Update Feature Mapping
     *
     * @param featureMappingDTO
     * @return
     */
    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestParam(value = "featureMappingDTO") String featureMappingDTO) {
        ApiResponse response = new ApiResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            FeatureMappingDTO reqModel = mapper.readValue(featureMappingDTO, FeatureMappingDTO.class);
            if (reqModel.getId() != null) {
                response.setData(featureMappingService.update(reqModel.getId(), reqModel));
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
     * Delete Feature Mapping
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam @Valid Long id) {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(featureMappingService.delete(id));
            response.setMessage(messageSource.getMessage("api.delete.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    /**
     * Get Feature mapping list
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<Object> getList() {
        ApiResponse response = new ApiResponse(false);
        try {
            response.setData(featureMappingService.findAllFeatureMapping());
            response.setMessage(messageSource.getMessage("api.list.success", null, null));
            response.setSuccess(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
