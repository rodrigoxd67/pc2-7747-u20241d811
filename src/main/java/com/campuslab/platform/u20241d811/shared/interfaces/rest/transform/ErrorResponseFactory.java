package com.campuslab.platform.u20241d811.shared.interfaces.rest.transform;

import com.campuslab.platform.u20241d811.shared.interfaces.rest.resources.ErrorResource;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import java.util.Locale;

/**
 * Builds JSON error responses with localized messages.
 * @author rodrigoxd67
 */
@Component
public class ErrorResponseFactory {
    private final MessageSource messages;
    /**
     * Injects the application message source.
     * @param messages translation bundles
     */
    public ErrorResponseFactory(MessageSource messages) { this.messages = messages; }
    /**
     * Resolves a message key and builds an error response.
     * @param status HTTP status
     * @param messageKey translation key
     * @param locale response language
     * @return localized JSON error
     */
    public ResponseEntity<ErrorResource> create(HttpStatus status, String messageKey, Locale locale) {
        return fromMessage(status, messages.getMessage(messageKey, null, locale), locale);
    }
    /**
     * Builds a response for an already localized validation message.
     * @param status HTTP status
     * @param message localized message
     * @param locale response language
     * @return JSON error containing only message
     */
    public ResponseEntity<ErrorResource> fromMessage(HttpStatus status, String message, Locale locale) {
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_LANGUAGE, locale.toLanguageTag()).body(new ErrorResource(message));
    }
}
