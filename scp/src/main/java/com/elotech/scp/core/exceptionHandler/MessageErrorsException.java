package com.elotech.scp.core.exceptionHandler;

import com.elotech.scp.core.exceptionHandler.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class MessageErrorsException extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({InvalidValueException.class})
    protected ResponseEntity<Object> handleInvalidValueException(InvalidValueException ex, WebRequest request) {
        ErrorHelper responseError = new ErrorHelper(ex.getMessageErrors(), ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        ErrorHelper responseError = new ErrorHelper(Collections.singletonList("Registro não encontrado"), ex.getClass(), ex.getMessage());
        return handleExceptionInternal(ex,responseError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    public static class ErrorHelper {
        private List<?> errors;
        private Class<?> classException;
        private String message;

        public ErrorHelper(List<?> errors, Class<?> classException, String message) {
            this.errors = errors;
            this.classException = classException;
            this.message = message;
        }

        public List<?> getErrors() {
            return errors;
        }

        public Class<?> getClassException() {
            return classException;
        }

        public String getMessage() {
            return message;
        }
    }

}
