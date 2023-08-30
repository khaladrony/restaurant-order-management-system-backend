package com.rony.restaurant.models;


import com.rony.restaurant.entity.ImageUpload;
import lombok.Data;

@Data
public class ImageUploadDTO {
    private Long id;
    private String name;
    private String type;
    private byte[] picByte;

    public ImageUploadDTO(ImageUpload imageUpload){
        this.setName(imageUpload.getName());
        this.setType(imageUpload.getType());
        this.setPicByte(imageUpload.getPicByte());
    }

    public ImageUploadDTO(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
}
