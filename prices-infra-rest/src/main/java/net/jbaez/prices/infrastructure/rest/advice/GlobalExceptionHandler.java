package net.jbaez.prices.infrastructure.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * Controlador capturar y manejar excepciones globales en la API REST.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura errores de tipo de argumento, como fechas mal formadas en la URL.
     *
     * @param ex Excepcion de tipo de argumento.
     * @return Detalle del problema estructurado.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                        ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName())
        );
        problemDetail.setTitle("Parameter Type Mismatch");
        problemDetail.setType(URI.create("https://api.jbaez.net/errors/type-mismatch"));
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    /**
     * Captura cualquier otra excepcion no controlada.
     *
     * @param ex Excepcion general.
     * @return Detalle del problema estructurado con un error 500.
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralError(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred in the system."
        );
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("https://api.jbaez.net/errors/internal-server-error"));
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
}
