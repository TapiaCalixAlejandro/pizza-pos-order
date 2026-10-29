package com.msvc.order.shared.exception;

import com.msvc.order.shared.response.ApiErrorResponse;
import com.msvc.order.shared.response.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ResponseFactory responseFactory;

    public GlobalExceptionHandler(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseFactory.error(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException ex) {

        return ResponseEntity.badRequest()
                .body(responseFactory.error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflictException(ConflictException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(responseFactory.error(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        LOGGER.error("Unexpected error while processing request.", ex);

        return ResponseEntity
                .internalServerError()
                .body(
                        responseFactory
                                .error(
                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                "An unexpected error occurred. Please contact support.",
                                        List.of(ex.getMessage())
                                )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> details = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest()
                .body(
                        responseFactory
                                .error(
                                        HttpStatus.BAD_REQUEST,
                                        "Request validation failed.",
                                        details
                                )
                );
    }

    @ExceptionHandler(CatalogServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleCatalogServiceException(CatalogServiceException exception) {

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        responseFactory.error(
                                HttpStatus.SERVICE_UNAVAILABLE,
                                exception.getMessage()
                        )
                );
    }

}
