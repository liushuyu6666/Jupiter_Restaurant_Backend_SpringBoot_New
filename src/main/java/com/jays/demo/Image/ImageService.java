package com.jays.demo.Image;

import com.jays.demo.S3.S3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    S3Repository s3Repository;

    public Image uploadImage(String imageName, MultipartFile multipartFile, String userId, boolean isPublic) throws Exception {
        String imageId = UUID.randomUUID().toString();

        String key = this.s3Repository.uploadFile(imageId, multipartFile, "images");

        Image image = new Image(userId, imageName, key, isPublic);
        return this.imageRepository.save(image);
    }

    public List<Image> listImages(String userId) {
        List<Image> images = this.imageRepository.findByUserId(userId);
        return images.stream().filter(image -> this.s3Repository.objectExists(image.getKey())).toList();
    }

    public List<Image> listPublicImages() {
        List<Image> images = this.imageRepository.findByIsPublic(true);
        return images.stream().filter(image -> this.s3Repository.objectExists(image.getKey())).toList();
    }
}
