package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Stores a failure type and message key.
 * @param <T> result value type
 * @param type failure classification
 * @param messageKey localization key
 * @author rodrigoxd67
 */
public record Failure<T>(FailureType type, String messageKey) implements Result<T> {
    /**
     * Validates the failure type and message key.
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
