package com.crud.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(409, "Username Already Exists", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(404, "User Not Found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(409, "User Already Exists", HttpStatus.BAD_REQUEST),
    USER_NOT_LOGGED_IN(401, "User Not Logged In", HttpStatus.UNAUTHORIZED),
    USER_LOGIN_FAILED(402, "User Login Failed", HttpStatus.UNAUTHORIZED),
    SYSTEM_ERROR(500, "System Error", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCESS_DENIED(403, "Access Denied", HttpStatus.FORBIDDEN),
    ACCESS_DENIED_ERROR(403, "Access Denied", HttpStatus.FORBIDDEN),
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists", HttpStatus.CONFLICT),
    EMAIL_NOT_EXISTS(409, "Email Not Exists", HttpStatus.CONFLICT),
    INVALID_PASSWORD(409, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_BIRTHDAY(409, "Age must be at least {min}", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "Unauthorized", HttpStatus.FORBIDDEN),
    UNCAUGHT_EXCEPTION(500, "Uncaught Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_USERNAME(1003, "Username must at least {min} characters", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_REQUIRED(1005, "Authentication Required", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_EXPIRED(1006, "Authentication Expired", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_FAILED_EXPIRED(1007, "Authentication Failed Expired", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_EXPIRED_EXPIRED(1008, "Authentication Expired Expired", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1009, "Token Expired", HttpStatus.UNAUTHORIZED),
    TOKEN_NOT_EXPIRED(1010, "Token Not Expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(1011, "Token Invalid", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(1012, "Invalid Key", HttpStatus.BAD_REQUEST),

    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

}
