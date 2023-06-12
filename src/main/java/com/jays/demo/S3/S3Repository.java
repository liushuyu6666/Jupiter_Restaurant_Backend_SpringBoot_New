package com.jays.demo.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Repository
public class S3Repository {
    private AmazonS3 s3client;

    @Value("${aws.S3.properties.bucketName}")
    private String bucketName;

    @Value("${aws.S3.properties.accessKey}")
    private String accessKey;

    @Value("${aws.S3.properties.secretKey}")
    private String secretKey;

    @Value("${aws.S3.properties.region}")
    private String region;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private boolean existsBucket() throws Exception {
        HeadBucketRequest headBucketRequest = new HeadBucketRequest(this.bucketName);
        try {
            /* To use this operation, you must have permissions to perform the s3:ListBucket action.
             * The bucket owner has this permission by default and can grant this permission to others.*/
            this.s3client.headBucket(headBucketRequest);
            return true;
        } catch (AmazonServiceException amazonServiceException) {
            Integer code = amazonServiceException.getStatusCode();
            if(code.equals(404)) {
                return false;
            } else {
                throw new Exception(code.toString());
            }
        }
    }

    public String uploadFile(String fileId, MultipartFile multipartFile, String prefix) throws Exception{
        if(!this.existsBucket()) {
            throw new Exception("No such bucket in S3");
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        String key = prefix + "/" + fileId;
        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata);
        s3client.putObject(putObjectRequest);

        return key;
    }
}
