package com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects;

/**
 * Identifies an equipment reservation by its code.
 * @param code required reservation code
 * @author rodrigoxd67
 */
public record ReservationCode(String code) {
    /**
     * Validates the reservation code.
     * @param code nonblank reservation code
     * @throws IllegalArgumentException if code is null or blank
     */
    public ReservationCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Reservation code must not be null or blank.");
        }
    }
}
