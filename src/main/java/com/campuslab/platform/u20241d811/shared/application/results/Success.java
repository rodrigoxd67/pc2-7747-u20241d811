package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Carries the value produced by a successful application operation.
 * @param <T> successful value type
 * @param value value returned to the caller
 * @author rodrigoxd67
 */
public record Success<T>(T value) implements Result<T> {
    /**
     * Constructs a successful result with a value.
     * @param value successful value, required
     * @throws IllegalArgumentException if value is null
     */
    public Success {
        if (value == null) {
            throw new IllegalArgumentException("A successful result must contain a value.");
        }
    }
}
