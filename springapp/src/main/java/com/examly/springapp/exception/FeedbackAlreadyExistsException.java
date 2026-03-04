package com.examly.springapp.exception;

public class FeedbackAlreadyExistsException extends RuntimeException {
    public FeedbackAlreadyExistsException(String message){
        super(message);
    }
    
}
