package com.sunland.new_im.websocket.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 标准json返回结果基类
 * Created by 李小凡 on 2018/1/30.
 * "flag": "1",  返回状态码 值为1时表示正常返回
 * "error": "error", 返回错误信息，当flag不为1时才有值
 * "resultMessage": T  返回信息，当flag为1时才有值
 */

public class SunlandBaseResult<T> implements Serializable {

    /**
     * flag : 0
     * error : error
     * resultMessage : null
     */

    @SerializedName("flag")
    private String flag;
    @SerializedName("error")
    private String error;
    @SerializedName("resultMessage")
    private T resultMessage;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(T resultMessage) {
        this.resultMessage = resultMessage;
    }
}
