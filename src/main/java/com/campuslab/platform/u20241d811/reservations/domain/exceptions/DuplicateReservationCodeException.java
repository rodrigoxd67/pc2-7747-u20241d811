package com.campuslab.platform.u20241d811.reservations.domain.exceptions;

/**
 * Reports an existing reservation code.
 * @author rodrigoxd67
 */
public class DuplicateReservationCodeException extends RuntimeException {
    /** Creates a duplicate-code exception. */
    public DuplicateReservationCodeException() {
        super("A reservation with this code already exists.");
    }
}
