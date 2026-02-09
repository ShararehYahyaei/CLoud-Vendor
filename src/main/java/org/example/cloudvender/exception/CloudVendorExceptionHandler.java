package org.example.cloudvender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CloudVendorExceptionHandler {

    @ExceptionHandler(CloudVendorNotFoundException.class)
    public ResponseEntity<CloudVendorException> handleCloudNotFoundException(
            CloudVendorNotFoundException e) {

        CloudVendorException exception = new CloudVendorException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}



