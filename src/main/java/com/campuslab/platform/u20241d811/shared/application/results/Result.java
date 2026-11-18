package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Represents the success or failure of an application operation.
 * @param <T> successful result value type
 * @author rodrigoxd67
 */
public sealed interface Result<T> permits Success, Failure {
    /**
     * Creates a successful result.
     * @param value successful value, required
     * @param <T> value type
     * @return successful outcome
     * @throws IllegalArgumentException if value is null
     */
    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a failure with a type and message key.
     * @param type application failure classification
     * @param messageKey nonblank localization key
     * @param <T> result value type
     * @return failed outcome
     * @throws IllegalArgumentException if type or messageKey is invalid
     */
    static <T> Result<T> failure(FailureType type, String messageKey) {
        return new Failure<>(type, messageKey);
    }

    /**
     * Checks whether this result is successful.
     * @return true for Success, false for Failure
     */
    default boolean isSuccess() {
        return this instanceof Success<?>;
    }
}
