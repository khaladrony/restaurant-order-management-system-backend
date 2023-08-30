package com.rony.restaurant.services.imageUpload;

import com.rony.restaurant.entity.ImageUpload;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.ImageUploadDTO;
import com.rony.restaurant.repository.ImageUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    ImageUploadRepository imageUploadRepository;

    @Override
    public Optional<ImageUpload> findByName(String name) {
        return imageUploadRepository.findByName(name);
    }

    @Override
    public ImageUploadDTO create(ImageUploadDTO imageUploadDTO) throws ServiceException {
        imageUploadRepository.save(new ImageUpload(imageUploadDTO));
        return imageUploadDTO;
    }

    @Override
    public ImageUploadDTO update(Long id, ImageUploadDTO imageUploadDTO) throws ServiceException {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        return null;
    }

    @Override
    public ImageUploadDTO findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public Page<ImageUploadDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<ImageUploadDTO> findAll() {
        return null;
    }
}
