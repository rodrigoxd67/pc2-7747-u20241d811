package com.campuslab.platform.u20241d811.reservations.domain.model.aggregates;

import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.*;
import com.campuslab.platform.u20241d811.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

/**
 * Represents an equipment reservation and enforces its required values.
 * @author rodrigoxd67
 */
public class EquipmentReservation extends AbstractDomainAggregateRoot {
    private final ReservationCode reservationCode;
    private final LaboratoryId laboratoryId;
    private final ReservationPeriod reservationPeriod;
    private final EquipmentBatch equipmentBatch;
    private final Integer accessLevel;
    private final ReservationStatus status;
    private final String reservationNotes;

    /**
     * Creates a pending reservation.
     * @param reservationCode reservation code
     * @param laboratoryId external laboratory identifier
     * @param reservationPeriod reservation dates
     * @param equipmentBatch equipment quantity and unit
     * @param accessLevel required access level from 1 to 5
     * @param reservationNotes optional notes
     * @throws IllegalArgumentException if a required value is invalid
     */
    public EquipmentReservation(ReservationCode reservationCode, LaboratoryId laboratoryId,
            ReservationPeriod reservationPeriod, EquipmentBatch equipmentBatch,
            Integer accessLevel, String reservationNotes) {
        this(reservationCode, laboratoryId, reservationPeriod, equipmentBatch,
                accessLevel, ReservationStatus.PENDING, reservationNotes);
    }

    /**
     * Creates a reservation with an explicit status.
     * @param reservationCode reservation code
     * @param laboratoryId external laboratory identifier
     * @param reservationPeriod reservation dates
     * @param equipmentBatch equipment quantity and unit
     * @param accessLevel required access level from 1 to 5
     * @param status required reservation status
     * @param reservationNotes optional notes
     * @throws IllegalArgumentException if a required value is invalid
     */
    public EquipmentReservation(ReservationCode reservationCode, LaboratoryId laboratoryId,
            ReservationPeriod reservationPeriod, EquipmentBatch equipmentBatch,
            Integer accessLevel, ReservationStatus status, String reservationNotes) {
        super();
        if (reservationCode == null) {
            throw new IllegalArgumentException("Reservation code is required.");
        }
        if (laboratoryId == null) {
            throw new IllegalArgumentException("Laboratory identifier is required.");
        }
        if (reservationPeriod == null) {
            throw new IllegalArgumentException("Reservation period is required.");
        }
        if (equipmentBatch == null) {
            throw new IllegalArgumentException("Equipment batch is required.");
        }
        if (accessLevel == null || accessLevel < 1 || accessLevel > 5) {
            throw new IllegalArgumentException("Access level must be between 1 and 5.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Reservation status must not be null.");
        }
        this.reservationCode = reservationCode;
        this.laboratoryId = laboratoryId;
        this.reservationPeriod = reservationPeriod;
        this.equipmentBatch = equipmentBatch;
        this.accessLevel = accessLevel;
        this.status = status;
        this.reservationNotes = reservationNotes;
    }

    /**
     * Returns the reservation code.
     * @return reservation code
     */
    public ReservationCode getReservationCode() { return reservationCode; }

    /**
     * Returns the laboratory identifier.
     * @return laboratory identifier
     */
    public LaboratoryId getLaboratoryId() { return laboratoryId; }

    /**
     * Returns the reservation period.
     * @return reservation dates
     */
    public ReservationPeriod getReservationPeriod() { return reservationPeriod; }

    /**
     * Returns the equipment batch.
     * @return equipment quantity and unit
     */
    public EquipmentBatch getEquipmentBatch() { return equipmentBatch; }

    /**
     * Returns the access level.
     * @return access level between 1 and 5
     */
    public Integer getAccessLevel() { return accessLevel; }

    /**
     * Returns the reservation status.
     * @return reservation status
     */
    public ReservationStatus getStatus() { return status; }

    /**
     * Returns the reservation notes.
     * @return optional notes, or null
     */
    public String getReservationNotes() { return reservationNotes; }
}
