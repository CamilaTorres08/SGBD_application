package edu.eci.sgbd.application.exception;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND,message);
    }
}