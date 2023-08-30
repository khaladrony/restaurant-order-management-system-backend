package com.rony.restaurant.entity;

import com.rony.restaurant.models.ImageUploadDTO;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "image_upload")
public class ImageUpload {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    public ImageUpload() {
        super();
    }

    public ImageUpload(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ImageUpload(ImageUploadDTO imageUploadDTO){
        this.setName(imageUploadDTO.getName());
        this.setType(imageUploadDTO.getType());
        this.setPicByte(imageUploadDTO.getPicByte());
    }
}


