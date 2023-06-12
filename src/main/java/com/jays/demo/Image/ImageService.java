package com.jays.demo.Image;

import com.jays.demo.S3.S3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    S3Repository s3Repository;

    public Image uploadImage(String imageName, MultipartFile multipartFile, String userId, boolean isPublic) throws Exception {
        String imageId = UUID.randomUUID().toString();

        String url = this.s3Repository.uploadFile(imageId, multipartFile, "images");

        Image image = new Image(userId, imageName, url, isPublic);
        return this.imageRepository.save(image);
    }

    public List<Image> listImages(String userId) {
        return this.imageRepository.findByUserId(userId);
    }

    public List<Image> listPublicImages() {
        return this.imageRepository.findByIsPublic(true);
    }
}
