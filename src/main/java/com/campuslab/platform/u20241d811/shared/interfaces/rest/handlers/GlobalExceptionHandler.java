package com.campuslab.platform.u20241d811.shared.interfaces.rest.handlers;

import com.campuslab.platform.u20241d811.shared.interfaces.rest.resources.ErrorResource;
import com.campuslab.platform.u20241d811.shared.interfaces.rest.transform.ErrorResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Converts request exceptions into localized JSON errors.
 * @author rodrigoxd67
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final ErrorResponseFactory errors;
    /**
     * Injects the localized response factory.
     * @param errors error response factory
     */
    public GlobalExceptionHandler(ErrorResponseFactory errors) { this.errors = errors; }
    /**
     * Reports request field validation failures.
     * @param exception field validation errors
     * @return HTTP 400 with a localized message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> validation(MethodArgumentNotValidException exception) {
        var locale = LocaleContextHolder.getLocale();
        var fieldErrors = exception.getBindingResult().getFieldErrors();
        if (fieldErrors.isEmpty()) {
            return errors.create(HttpStatus.BAD_REQUEST, "reservation.request.invalid", locale);
        }
        return errors.fromMessage(HttpStatus.BAD_REQUEST, fieldErrors.getFirst().getDefaultMessage(), locale);
    }
    /**
     * Reports malformed JSON and invalid request arguments.
     * @return HTTP 400 error
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResource> invalidRequest() {
        return errors.create(HttpStatus.BAD_REQUEST, "reservation.request.invalid", LocaleContextHolder.getLocale());
    }
    /**
     * Rejects unsupported HTTP methods.
     * @param exception supported HTTP methods
     * @return HTTP 405 with its Allow header
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResource> method(HttpRequestMethodNotSupportedException exception) {
        var response = errors.create(HttpStatus.METHOD_NOT_ALLOWED, "request.method.invalid", LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).headers(response.getHeaders())
                .allow(exception.getSupportedHttpMethods().toArray(HttpMethod[]::new)).body(response.getBody());
    }
    /**
     * Preserves framework statuses and hides internal failure details.
     * @param exception request failure
     * @return localized error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> unexpected(Exception exception) {
        var locale = LocaleContextHolder.getLocale();
        if (exception instanceof ErrorResponse response) {
            var status = HttpStatus.valueOf(response.getStatusCode().value());
            String key = switch (status) {
                case UNSUPPORTED_MEDIA_TYPE -> "request.media.invalid";
                case NOT_ACCEPTABLE -> "response.media.invalid";
                case NOT_FOUND -> "request.resource.notFound";
                default -> status.is5xxServerError() ? "reservation.registration.failed" : "reservation.request.invalid";
            };
            return errors.create(status, key, locale);
        }
        LOGGER.error("Unexpected request failure: {}", exception.getClass().getSimpleName());
        return errors.create(HttpStatus.INTERNAL_SERVER_ERROR, "reservation.registration.failed", locale);
    }
}
