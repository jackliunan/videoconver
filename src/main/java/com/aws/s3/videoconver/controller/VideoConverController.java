package com.aws.s3.videoconver.controller;

import com.aws.s3.videoconver.bean.ResultMap;
import com.aws.s3.videoconver.bean.VideoConverReq;
import com.aws.s3.videoconver.service.VideoConverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: haoming_yu
 * @Date: 2023/2/15 22:49
 * @Description:
 */
@Controller
@RequestMapping("/videoConver")
public class VideoConverController {

    @Autowired
    private VideoConverService videoConverService;

    @PostMapping("/doConver")
    public ResultMap doConver(@RequestBody VideoConverReq videoConverReq){
        ResultMap resultMap = new ResultMap();
        try {
            videoConverService.doConver(videoConverReq);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.setSuccess(false);
        }
        return resultMap;
    }
}
