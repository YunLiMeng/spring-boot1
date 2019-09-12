package com.example.demo.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ author: limeng
 * @ date: Created in 2019/9/12 17:13
 * @ description：封装httpClient响应结果
 */
@Data
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }
}
