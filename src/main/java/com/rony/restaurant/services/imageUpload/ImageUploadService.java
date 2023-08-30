package com.rony.restaurant.services.imageUpload;


import com.rony.restaurant.entity.ImageUpload;
import com.rony.restaurant.models.ImageUploadDTO;
import com.rony.restaurant.services.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ImageUploadService extends AbstractService<ImageUploadDTO> {

    Optional<ImageUpload> findByName(String name);
}
