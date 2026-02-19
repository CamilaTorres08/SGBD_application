package edu.eci.sgbd.application.exception;

public class BadRequestException extends AppException {
    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST,message);
    }
}
