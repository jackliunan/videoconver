package com.aws.s3.videoconver.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: haoming_yu
 * @Date: 2023/2/15 15:23
 * @Description:
 */
public class S3Utils {

    public static void GetObjectSummaries(AmazonS3 s3Client, String bucket_name) {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
    }

    //上传文件到桶
    public static void PutObject(AmazonS3 s3Client, String file_path,String bucket_name) {
        String key_name = Paths.get(file_path).getFileName().toString();
        try {
            s3Client.putObject(bucket_name, key_name, new File(file_path));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        System.err.println("Done!");
    }

    //下载指定文件(key_name桶名)
    public static void GetObject(AmazonS3 s3Client, String key_name, String bucket_name) {
        try {
            S3Object o = s3Client.getObject(bucket_name, key_name);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(key_name));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done!");
    }

    public static void main(String[] args) {
    }
}