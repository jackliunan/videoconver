package com.aws.s3.videoconver.bean;

/**
 * @Auther: haoming_yu
 * @Date: 2022/4/12 23:17
 * @Description:
 */
public class ResultMap<T> {

    public ResultMap() {
        this.info = "";
        this.success = true;
    }

    public ResultMap(String info, boolean success) {
        this.info = info;
        this.success = success;
    }

    public ResultMap(String info, boolean success, T data) {
        this.info = info;
        this.data = data;
        this.success = success;
    }

    private String info;

    private boolean success;

    private T data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
