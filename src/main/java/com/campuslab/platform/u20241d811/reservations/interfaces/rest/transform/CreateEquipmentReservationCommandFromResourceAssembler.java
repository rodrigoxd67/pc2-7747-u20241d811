package com.campuslab.platform.u20241d811.reservations.interfaces.rest.transform;

import com.campuslab.platform.u20241d811.reservations.application.commands.CreateEquipmentReservationCommand;
import com.campuslab.platform.u20241d811.reservations.interfaces.rest.resources.CreateEquipmentReservationResource;

/**
 * Converts a request resource into a registration command.
 * @author rodrigoxd67
 */
public final class CreateEquipmentReservationCommandFromResourceAssembler {
    private CreateEquipmentReservationCommandFromResourceAssembler() { }
    /**
     * Creates a registration command.
     * @param resource validated request resource
     * @return registration command
     */
    public static CreateEquipmentReservationCommand toCommandFromResource(CreateEquipmentReservationResource resource) {
        return new CreateEquipmentReservationCommand(resource.getReservationCode(), resource.getLaboratoryId(),
                resource.getReservationPeriodStartDate(), resource.getReservationPeriodEndDate(),
                resource.getEquipmentBatchQuantity(), resource.getEquipmentBatchUnit(), resource.getAccessLevel(),
                resource.getStatus(), resource.getReservationNotes());
    }
}
