package com.devsDoAgi.SAFeR.exception;

public class DeviceNotFound extends RuntimeException{
    public DeviceNotFound(String erroMessage){
        super(erroMessage);
    }
}