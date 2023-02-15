package com.aws.s3.videoconver.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.aws.s3.videoconver.bean.VideoConverReq;
import com.aws.s3.videoconver.util.CreateJob;
import com.aws.s3.videoconver.util.S3Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.mediaconvert.MediaConvertClient;

/**
 * @Auther: haoming_yu
 * @Date: 2023/2/15 22:45
 * @Description:
 */
@Service
public class VideoConverServiceImpl implements VideoConverService{

    @Value("${aws.s3.access_key_id}")
    private String access_key_id;
    @Value("${aws.s3.secret_key_id}")
    private String secret_key_id;
    @Value("${aws.s3.bucket_name}")
    private String bucket_name;
    @Value("${aws.s3.mcRoleARN}")
    private String mcRoleARN;

    @Override
    public String doConver(VideoConverReq videoConverReq) {
        // 创建MediaConvert连接
        String id = null;
        Region region = Region.AP_NORTHEAST_1;
        MediaConvertClient mc = MediaConvertClient.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        try{
            // 创建MediaConvert转换任务，获得的id可用来检测job的状态
            id = CreateJob.createMediaJob(mc, mcRoleARN, videoConverReq.getUrl());
            System.out.println("MediaConvert job created. Job Id = " +id );
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            mc.close();
        }
        return id;
    }

    private AmazonS3 getAmazonS3Client(){
        //通过sdk创建与S3的链接
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(access_key_id, secret_key_id);
        return AmazonS3Client.builder()
                .withRegion(Regions.AP_NORTHEAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
