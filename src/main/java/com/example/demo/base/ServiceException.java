package com.example.demo.base;


public class ServiceException extends RuntimeException {

    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Throwable e){
        super(message,e);
    }
}
