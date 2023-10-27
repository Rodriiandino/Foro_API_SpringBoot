package com.one.foroapi.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
            String errorMessage = ex.getMessage();
            CustomErrorResponse error = new CustomErrorResponse(errorMessage, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler({notEditableException.class})
        public ResponseEntity<CustomErrorResponse> handleNotEditableException(Exception ex) {
            String errorMessage = ex.getMessage();
            CustomErrorResponse error = new CustomErrorResponse(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY.value());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
        }


        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
            CustomErrorResponse error = new CustomErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }

}
