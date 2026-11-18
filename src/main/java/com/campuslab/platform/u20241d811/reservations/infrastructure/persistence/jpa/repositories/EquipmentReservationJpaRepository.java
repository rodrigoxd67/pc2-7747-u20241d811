package com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.repositories;

import com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.entities.EquipmentReservationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides JPA operations for equipment reservations.
 * @author rodrigoxd67
 */
public interface EquipmentReservationJpaRepository extends JpaRepository<EquipmentReservationPersistenceEntity, Long> {
    /**
     * Checks whether a reservation code is stored.
     * @param reservationCode code to search
     * @return true if registered
     */
    boolean existsByReservationCode(String reservationCode);
}
