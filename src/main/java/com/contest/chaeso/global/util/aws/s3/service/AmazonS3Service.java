package com.contest.chaeso.global.util.aws.s3.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonS3Client amazonS3Client;


    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file){

        return upload(file);

    }


    public String updateFile(){
        
//        db에서 review id에 해당하는 url 가져와서 삭제
//        다시 upload 후 delete 하기
//                

        return null;
    }


    public void deleteFile(String fileUrl){
        delete(fileUrl);

    }

    private void delete(String fileUrl) {
        try {
            amazonS3Client.deleteObject(bucket, fileUrl);
        } catch (SdkClientException e) {
            throw new CustomException(ErrorCode.S3_FILE_UPLOAD);
        }
    }


    private String upload(MultipartFile file) {
        String fileUrl = "None";
        try {
            String fileName = "chaeso/" + file.getOriginalFilename();
            fileUrl= "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;
            ObjectMetadata metadata= new ObjectMetadata();

            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            return fileUrl;

        } catch (IOException e) {

            log.info("[error] : " + e.getMessage());
            throw new CustomException(ErrorCode.S3_FILE_UPLOAD);
        }
    }


}
