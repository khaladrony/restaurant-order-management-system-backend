package com.rony.restaurant.controllers;


import com.rony.restaurant.models.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/file-upload")
public class FileUploadController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping(value = "/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> handleFileUploadForm(@RequestPart("file") MultipartFile file) throws IOException {

        log.info("handling request parts: {}", file);
        ApiResponse response = new ApiResponse(false);

        try {

            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "image");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(file.getOriginalFilename())
                    .toUriString();

//            Map result = Map.of(
//                    "filename", file.getOriginalFilename(),
//                    "fileUri", fileUri
//            );

            Map map = new HashMap();
            map.put("filename", file.getOriginalFilename());
            map.put("fileUri", fileUri);

            response.setData(map);
            response.setMessage(messageSource.getMessage("api.create.success", null, null));
            response.setSuccess(true);

            return ResponseEntity.status(HttpStatus.OK).body(response);


        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
