package com.training.demo.common.exception;


public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String email) {
        super(email + " already exist");
    }
}
