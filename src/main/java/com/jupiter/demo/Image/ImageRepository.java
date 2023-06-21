package com.jupiter.demo.Image;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findByUserId(String userId);

    List<Image> findByIsPublic(boolean isPublic);
}
