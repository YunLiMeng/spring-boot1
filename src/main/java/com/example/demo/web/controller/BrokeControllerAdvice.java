package com.example.demo.web.controller;

import com.example.demo.base.ServiceException;
import com.example.demo.common.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@ResponseBody
//@ResponseStatus
public class BrokeControllerAdvice implements ResponseBodyAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(BrokeControllerAdvice.class);
    @ExceptionHandler
    public Object handleException(Exception e){
        LOG.error(e.getMessage(),e);
        ResponseMessage message = new ResponseMessage();
        if(e instanceof ServiceException){
            ServiceException exception = ((ServiceException)e);
            message.setCode(201);
            message.setMsg(StringUtils.trimToEmpty("错误信息！") + " " + StringUtils.trimToEmpty(e.getMessage()));
        }else{
            message.setCode(500);
            message.setMsg(e.getMessage());
        }
        return message;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof byte[]){
            return body;
        }else if(body instanceof ResponseMessage){
            return  body;
        }else if(body instanceof String){
            return body;
        }else {
            return ResponseMessage.Success(body);
        }
    }
}
