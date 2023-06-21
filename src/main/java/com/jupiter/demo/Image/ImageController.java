package com.jupiter.demo.Image;

import com.jupiter.demo.Auth.AuthService;
import com.jupiter.demo.Payload.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RequestMapping("/v1/jupiter")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    AuthService authService;

    @PostMapping("/images")
    public ResponseEntity<?> uploadImageToS3(
            @RequestHeader("Authorization") String authentication,
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "name") String imageName,
            @RequestParam(value = "isPublic") boolean isPublic
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);

            this.imageService.uploadImage(imageName, multipartFile, userId, isPublic);

            return ResponseEntity.ok("Image " + imageName + " uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/images")
    public ResponseEntity<?> listImages(
            @RequestHeader("Authorization") String authentication
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);
            List<Image> images = this.imageService.listImages(userId);

            com.jupiter.demo.Payload.ResponseBody<List<Image>> responseBody = new com.jupiter.demo.Payload.ResponseBody<>(images, "", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/images/public")
    public ResponseEntity<?> listPublicImages(
    ) {
        List<Image> images = this.imageService.listPublicImages();

        com.jupiter.demo.Payload.ResponseBody<List<Image>> responseBody = new ResponseBody<>(images, "", null);
        return ResponseEntity.ok(responseBody);
    }
}
