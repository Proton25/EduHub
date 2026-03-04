package com.examly.springapp.exception;

public class EnrollmentAlreadyExistsException extends RuntimeException {
    public EnrollmentAlreadyExistsException(String message){
        super(message);
    }
}
