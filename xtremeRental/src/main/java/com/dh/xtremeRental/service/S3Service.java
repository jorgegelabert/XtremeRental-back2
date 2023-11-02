package com.dh.xtremeRental.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.dh.xtremeRental.entity.vm.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service

public class S3Service {
    private final static  String BUCKET = "1023c04-grupo3xr";

    @Autowired
    private AmazonS3Client s3Client;

    public String putObject(MultipartFile multipartFile){
        String extension= StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key= String.format("%s.%s", UUID.randomUUID(),extension);

        ObjectMetadata objectMetadata =new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET,key,multipartFile.getInputStream(),objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)    ;


            s3Client.putObject(putObjectRequest);
            return  key;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Asset getObject (String key) throws IOException {
        S3Object s3Object = s3Client.getObject(BUCKET,key);
        ObjectMetadata metadata = s3Object.getObjectMetadata();

        S3ObjectInputStream imputStream = s3Object.getObjectContent();

        byte[] bytes = IOUtils.toByteArray(imputStream);

        return new Asset(bytes, metadata.getContentType());

    }

    public void deleteObject (String key){
        s3Client.deleteObject(BUCKET,key);
    }

    public String getObjectURL (String key){
        return String.format("https://%s.s3.amazonaws.com/%s",BUCKET, key);
    }

}
