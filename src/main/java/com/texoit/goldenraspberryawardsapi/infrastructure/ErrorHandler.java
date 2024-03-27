package com.texoit.goldenraspberryawardsapi.infrastructure;

import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidDomainException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> handleNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementException> handleNoSuchElementError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ModelMap> handleBadRequestError(MethodArgumentNotValidException ex) {
        var errorsMap = new ModelMap();
        var errors = ex.getFieldErrors();
        errorsMap.addAttribute("errors",errors.stream().map(ValidationErrorDetail::new).toList());
        return ResponseEntity.badRequest().body(errorsMap);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ModelMap> handleBadRequestError(MethodArgumentTypeMismatchException ex) {
        var errorsMap = new ModelMap();
        var errors = new ArrayList<FieldError>();
        errors.add(new FieldError(ex.getName(), Objects.requireNonNull(ex.getPropertyName()), ex.getMessage()));
        errorsMap.addAttribute("errors", errors.stream().map(ValidationErrorDetail::new).toList());
        return ResponseEntity.badRequest().body(errorsMap);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ModelMap> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var errorsMap = new ModelMap();

        String customMessage = String.format(
                "Falha na integridade dos dados.%s",
                ex.getMessage().contains("Unique index or primary key violation") ? " Já existe um registro com os mesmos dados fornecidos." : ""
        );

        errorsMap.addAttribute("errors", Map.of("message", customMessage));
        return ResponseEntity.badRequest().body(errorsMap);
    }

    @ExceptionHandler(InvalidDomainException.class)
    public ResponseEntity<ModelMap> handleInvalidBeanFromCsvException(InvalidDomainException ex) {
        var errorsMap = new ModelMap();
        errorsMap.addAttribute("errors", Map.of("message", ex.getMessage()));
        return ResponseEntity.badRequest().body(errorsMap);
    }

    public record ValidationErrorDetail(String field, String message) {
        public ValidationErrorDetail(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
