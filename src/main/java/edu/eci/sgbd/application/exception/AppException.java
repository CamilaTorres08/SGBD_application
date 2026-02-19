package edu.eci.sgbd.application.exception;

public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    protected AppException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public int getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
