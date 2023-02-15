package com.aws.s3.videoconver.service;

import com.aws.s3.videoconver.bean.VideoConverReq;

public interface VideoConverService {

    String doConver(VideoConverReq videoConverReq);
}
