package br.com.cvc.configuration.rest.exceptionhandler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Component
@RestControllerAdvice
public class ApplicationHandler implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("code", HttpStatus.BAD_REQUEST.value());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleValidationExceptions(ConstraintViolationException ex) {

        Map<String, Object> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            errors.put("message", error.getMessage());
            errors.put("code", HttpStatus.BAD_REQUEST.value());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, Object> handleValidationNoSuchElementException(NoSuchElementException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("code", HttpStatus.NOT_FOUND.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NumberFormatException.class)
    public Map<String, Object> handleValueNumberFormatException(NumberFormatException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "The data format " + ex.getMessage() + " is invalid!");
        errors.put("code", HttpStatus.NOT_FOUND.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleValidationInternalException(Exception ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "The endpoint failed: " + ex.getMessage());
        errors.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, Object> handleValidationAccessDeniedException(AccessDeniedException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("code", HttpStatus.FORBIDDEN.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "The data integrity failed: " + ex.getCause().getCause().getMessage());
        errors.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public Map<String, Object> handleSQLException(SQLException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "SQL failed: " + ex.getMessage());
        errors.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, Object> handleIllegalStateException(IllegalStateException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("code", HttpStatus.BAD_REQUEST.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("code", HttpStatus.BAD_REQUEST.value());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, Object> handleBadCredentialsException(BadCredentialsException ex) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("code", HttpStatus.UNAUTHORIZED.value());
        return errors;
    }
}
