package com.rony.restaurant.services.imageUpload;

import com.rony.restaurant.entity.ImageUpload;
import com.rony.restaurant.exception.ServiceException;
import com.rony.restaurant.models.ImageUploadDTO;
import com.rony.restaurant.repository.ImageUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


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

    // compress the image bytes before storing it in the database
    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    @Override
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
