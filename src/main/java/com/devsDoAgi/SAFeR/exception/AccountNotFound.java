package com.devsDoAgi.SAFeR.exception;


public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String erroMessage){
        super(erroMessage);
    }
}
