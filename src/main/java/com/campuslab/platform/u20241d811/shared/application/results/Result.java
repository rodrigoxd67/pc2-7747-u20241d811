package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Represents an application outcome without depending on HTTP or persistence.
 * Exactly one outcome is returned: a successful value or a classified failure.
 * @param <T> successful result value type
 * @author rodrigoxd67
 */
public sealed interface Result<T> permits Success, Failure {
    /**
     * Creates a successful application outcome.
     * @param value successful value, required
     * @param <T> value type
     * @return successful outcome
     * @throws IllegalArgumentException if value is null
     */
    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a failure whose translation is deferred to the interfaces layer.
     * @param type application failure classification
     * @param messageKey nonblank localization key
     * @param <T> successful value type expected by the caller
     * @return failed outcome
     * @throws IllegalArgumentException if type or messageKey is invalid
     */
    static <T> Result<T> failure(FailureType type, String messageKey) {
        return new Failure<>(type, messageKey);
    }

    /**
     * Indicates whether the application operation succeeded.
     * @return true for Success, false for Failure
     */
    default boolean isSuccess() {
        return this instanceof Success<?>;
    }
}
