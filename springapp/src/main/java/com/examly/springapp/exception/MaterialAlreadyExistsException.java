package com.examly.springapp.exception;

public class MaterialAlreadyExistsException extends RuntimeException{
    public MaterialAlreadyExistsException(String message)
    {
        super(message);
    }
    
}
