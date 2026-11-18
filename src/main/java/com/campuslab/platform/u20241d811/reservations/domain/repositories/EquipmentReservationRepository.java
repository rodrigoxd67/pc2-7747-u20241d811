package com.campuslab.platform.u20241d811.reservations.domain.repositories;

import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.ReservationCode;

/**
 * Defines persistence operations for reservation registration.
 * @author rodrigoxd67
 */
public interface EquipmentReservationRepository {
    /**
     * Checks whether a reservation code is registered.
     * @param reservationCode code to search
     * @return true if the code exists
     */
    boolean existsByReservationCode(ReservationCode reservationCode);

    /**
     * Stores a reservation.
     * @param reservation reservation to store
     * @return stored reservation with its generated identity
     */
    EquipmentReservation save(EquipmentReservation reservation);
}
