package com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * Defines a reservation period with inclusive boundaries.
 * @param startDate first reservation date
 * @param endDate final reservation date
 * @author rodrigoxd67
 */
public record ReservationPeriod(LocalDate startDate, LocalDate endDate) {
    /**
     * Validates the reservation dates.
     * @param startDate required start date
     * @param endDate required date after startDate
     * @throws IllegalArgumentException if dates are null or unordered
     */
    public ReservationPeriod {
        if (startDate == null) {
            throw new IllegalArgumentException("Reservation start date is required.");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("Reservation end date is required.");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Reservation end date must be after the start date.");
        }
    }

    /**
     * Checks whether a date belongs to the period.
     * @param checkDate date to evaluate
     * @return true when the date is between both inclusive boundaries
     * @throws IllegalArgumentException if checkDate is null
     */
    public boolean contains(LocalDate checkDate) {
        if (checkDate == null) {
            throw new IllegalArgumentException("Check date is required.");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
