package com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects;

/**
 * Defines the allowed reservation statuses.
 * @author rodrigoxd67
 */
public enum ReservationStatus {
    /** Reservation awaiting confirmation. */
    PENDING,
    /** Confirmed reservation. */
    CONFIRMED,
    /** Completed reservation. */
    COMPLETED,
    /** Cancelled reservation. */
    CANCELLED
}
