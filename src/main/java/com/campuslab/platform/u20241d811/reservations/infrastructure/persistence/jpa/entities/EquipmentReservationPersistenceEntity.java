package com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.entities;

import com.campuslab.platform.u20241d811.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Maps equipment reservations to relational columns.
 * @author rodrigoxd67
 */
@Entity
@Table(name = "equipment_reservations", uniqueConstraints = @UniqueConstraint(
        name = "uk_equipment_reservation_code", columnNames = "reservation_code"))
@Getter
@Setter
public class EquipmentReservationPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(name = "reservation_code", nullable = false)
    private String reservationCode;
    @Column(name = "laboratory_id", nullable = false, length = 36)
    private String laboratoryId;
    @Column(name = "reservation_period_start_date", nullable = false)
    private LocalDate reservationPeriodStartDate;
    @Column(name = "reservation_period_end_date", nullable = false)
    private LocalDate reservationPeriodEndDate;
    @Column(name = "equipment_batch_quantity", nullable = false)
    private Integer equipmentBatchQuantity;
    @Lob
    @Column(name = "equipment_batch_unit", nullable = false)
    private String equipmentBatchUnit;
    @Column(name = "access_level", nullable = false)
    private Integer accessLevel;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ReservationStatus status;
    @Lob
    @Column(name = "reservation_notes")
    private String reservationNotes;
}
