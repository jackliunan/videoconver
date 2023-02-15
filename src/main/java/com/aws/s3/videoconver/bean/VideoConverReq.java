package com.aws.s3.videoconver.bean;

import lombok.Data;

/**
 * @Auther: haoming_yu
 * @Date: 2023/2/15 22:53
 * @Description:
 */
@Data
public class VideoConverReq {
    // 视频在s3的url
    private String url;
    // 转换类型
    private String toType;
}
