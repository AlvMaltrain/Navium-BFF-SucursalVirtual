package com.navium.bff_sucursal.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(FeignException.BadRequest ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", extractMessage(ex)));
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String, String>> handleNotFound(FeignException.NotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", extractMessage(ex)));
    }

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<Map<String, String>> handleConflict(FeignException.Conflict ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", extractMessage(ex)));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException ex) {
        return ResponseEntity.status(ex.status())
            .body(Map.of("error", extractMessage(ex)));
    }

    private String extractMessage(FeignException ex) {
    try {
        String body = ex.contentUTF8();
        if (body.contains("\"mensaje\"")) {
            return body.replaceAll(".*\"mensaje\":\\s*\"([^\"]+)\".*", "$1");
        }
        if (body.contains("\"message\"")) {
            return body.replaceAll(".*\"message\":\\s*\"([^\"]+)\".*", "$1");
        }
        if (body.contains("\"error\"")) {
            return body.replaceAll(".*\"error\":\\s*\"([^\"]+)\".*", "$1");
        }
        return body.isBlank() ? "Error en el servidor" : body;
    } catch (Exception e) {
        return "Error procesando la respuesta";
    }
}
}