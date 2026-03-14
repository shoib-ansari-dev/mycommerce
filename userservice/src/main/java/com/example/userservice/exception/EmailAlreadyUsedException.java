package com.example.userservice.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email){
        super("Email is already registerd "+ email);
    }
}
