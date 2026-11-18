package com.campuslab.platform.u20241d811.reservations.interfaces.rest.resources;

/**
 * Contains the public equipment reservation response fields.
 * @param id generated reservation identity
 * @param reservationCode reservation code
 * @param laboratoryId external laboratory UUID text
 * @param reservationPeriodStartDate ISO start date
 * @param reservationPeriodEndDate ISO end date
 * @param equipmentBatchQuantity equipment quantity
 * @param equipmentBatchUnit equipment unit
 * @param accessLevel access level from 1 to 5
 * @param status reservation status
 * @param reservationNotes optional notes
 * @author rodrigoxd67
 */
public record EquipmentReservationResource(Long id, String reservationCode, String laboratoryId,
        String reservationPeriodStartDate, String reservationPeriodEndDate,
        Integer equipmentBatchQuantity, String equipmentBatchUnit, Integer accessLevel,
        String status, String reservationNotes) { }
