package com.campuslab.platform.u20241d811.reservations.application.commands;

import java.time.LocalDate;

/**
 * Contains the data required to register an equipment reservation.
 * @param reservationCode reservation code
 * @param laboratoryId external laboratory UUID text
 * @param reservationPeriodStartDate first reservation date
 * @param reservationPeriodEndDate last reservation date
 * @param equipmentBatchQuantity equipment quantity
 * @param equipmentBatchUnit equipment unit
 * @param accessLevel required access level
 * @param status explicit reservation status
 * @param reservationNotes optional notes
 * @author rodrigoxd67
 */
public record CreateEquipmentReservationCommand(String reservationCode, String laboratoryId,
        LocalDate reservationPeriodStartDate, LocalDate reservationPeriodEndDate,
        Integer equipmentBatchQuantity, String equipmentBatchUnit, Integer accessLevel,
        String status, String reservationNotes) {
    /**
     * Creates a command with PENDING status.
     * @param reservationCode reservation code
     * @param laboratoryId external laboratory UUID text
     * @param reservationPeriodStartDate first reservation date
     * @param reservationPeriodEndDate last reservation date
     * @param equipmentBatchQuantity equipment quantity
     * @param equipmentBatchUnit equipment unit
     * @param accessLevel required access level
     * @param reservationNotes optional notes
     */
    public CreateEquipmentReservationCommand(String reservationCode, String laboratoryId,
            LocalDate reservationPeriodStartDate, LocalDate reservationPeriodEndDate,
            Integer equipmentBatchQuantity, String equipmentBatchUnit, Integer accessLevel,
            String reservationNotes) {
        this(reservationCode, laboratoryId, reservationPeriodStartDate, reservationPeriodEndDate,
                equipmentBatchQuantity, equipmentBatchUnit, accessLevel, "PENDING", reservationNotes);
    }
}
