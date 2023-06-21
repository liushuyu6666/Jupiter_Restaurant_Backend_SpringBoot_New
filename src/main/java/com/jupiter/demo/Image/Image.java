package com.jupiter.demo.Image;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Image")
public class Image {
    @Id
    private String imageId;
    @NotBlank
    private String userId;
    @NotBlank
    private String fileName;
    @NotBlank
    private String key;

    @NotBlank
    private boolean isPublic;
    @CreatedDate
    private Date uploadDate;

    public Image(String userId, String fileName, String key, boolean isPublic) {
        this.userId = userId;
        this.fileName = fileName;
        this.key = key;
        this.isPublic = isPublic;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
