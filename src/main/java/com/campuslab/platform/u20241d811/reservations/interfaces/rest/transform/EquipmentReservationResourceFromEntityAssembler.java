package com.campuslab.platform.u20241d811.reservations.interfaces.rest.transform;

import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.interfaces.rest.resources.EquipmentReservationResource;

/**
 * Converts a domain aggregate into its public response.
 * @author rodrigoxd67
 */
public final class EquipmentReservationResourceFromEntityAssembler {
    private EquipmentReservationResourceFromEntityAssembler() { }
    /**
     * Creates the response without audit fields.
     * @param reservation registered domain aggregate
     * @return flat response resource
     */
    public static EquipmentReservationResource toResourceFromEntity(EquipmentReservation reservation) {
        return new EquipmentReservationResource(reservation.getId(), reservation.getReservationCode().code(),
                reservation.getLaboratoryId().value().toString(), reservation.getReservationPeriod().startDate().toString(),
                reservation.getReservationPeriod().endDate().toString(), reservation.getEquipmentBatch().quantity(),
                reservation.getEquipmentBatch().unit(), reservation.getAccessLevel(), reservation.getStatus().name(),
                reservation.getReservationNotes());
    }
}
