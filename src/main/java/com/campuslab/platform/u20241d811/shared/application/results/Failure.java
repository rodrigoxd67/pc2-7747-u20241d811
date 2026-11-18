package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Describes an application failure without an HTTP status or localized text.
 * @param <T> successful value type expected by the caller
 * @param type failure classification
 * @param messageKey key to translate at the application boundary
 * @author rodrigoxd67
 */
public record Failure<T>(FailureType type, String messageKey) implements Result<T> {
    /**
     * Validates the failure metadata required by the interfaces layer.
     * @param type application failure classification, required
     * @param messageKey nonblank localization key
     * @throws IllegalArgumentException if metadata is missing
     */
    public Failure {
        if (type == null) {
            throw new IllegalArgumentException("Failure type is required.");
        }
        if (messageKey == null || messageKey.isBlank()) {
            throw new IllegalArgumentException("Failure message key is required.");
        }
    }
}
