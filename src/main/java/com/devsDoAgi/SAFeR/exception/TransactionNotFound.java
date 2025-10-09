package com.devsDoAgi.SAFeR.exception;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound(String erroMessage){
        super(erroMessage);
    }
}