package com.campuslab.platform.u20241d811.reservations.application.commandservices;

import com.campuslab.platform.u20241d811.reservations.application.commands.CreateEquipmentReservationCommand;
import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.shared.application.results.Result;

/**
 * Defines the equipment reservation registration use case.
 * @author rodrigoxd67
 */
public interface EquipmentReservationCommandService {
    /**
     * Registers an equipment reservation.
     * @param command registration data
     * @return successful reservation or classified failure
     */
    Result<EquipmentReservation> handle(CreateEquipmentReservationCommand command);
}
