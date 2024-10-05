package com.controle_financeiro.api_controlefinanceiro.exception_handler;

import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.records.responses.exceptions.ExceptionResponseRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {

        Optional<ExceptionsEnum> exceptionEnum = findExceptionEnumByMessage(ex.getMessage());

        if (exceptionEnum.isPresent()) {
            ExceptionsEnum enumValue = exceptionEnum.get();
            return new ResponseEntity<>(
                    createExceptionText(enumValue.getError_text()),
                    HttpStatus.resolve(enumValue.getError_code())
            );
        }

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.resolve(ExceptionsEnum.UNHANDLED_EXCEPTION.getError_code())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private Optional<ExceptionsEnum> findExceptionEnumByMessage(String message) {
        for (ExceptionsEnum exceptionEnum : ExceptionsEnum.values()) {
            if (exceptionEnum.name().equals(message)) {
                return Optional.of(exceptionEnum);
            }
        }
        return Optional.empty();
    }

    private ExceptionResponseRecord createExceptionText(String exceptionText) {
        return new ExceptionResponseRecord(exceptionText);
    }

}
