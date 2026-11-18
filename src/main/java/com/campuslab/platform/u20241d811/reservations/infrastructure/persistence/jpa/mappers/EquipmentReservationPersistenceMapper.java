package com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.mappers;

import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.*;
import com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.entities.EquipmentReservationPersistenceEntity;
import java.util.UUID;

/**
 * Converts between domain aggregates and persistence entities.
 * @author rodrigoxd67
 */
public final class EquipmentReservationPersistenceMapper {
    private EquipmentReservationPersistenceMapper() { }
    /**
     * Maps a reservation to its storage representation.
     * @param reservation domain aggregate
     * @return persistence entity with identity and audit dates
     */
    public static EquipmentReservationPersistenceEntity toEntity(EquipmentReservation reservation) {
        var entity = new EquipmentReservationPersistenceEntity();
        entity.setId(reservation.getId());
        entity.setCreatedAt(reservation.getCreatedAt());
        entity.setUpdatedAt(reservation.getUpdatedAt());
        entity.setReservationCode(reservation.getReservationCode().code());
        entity.setLaboratoryId(reservation.getLaboratoryId().value().toString());
        entity.setReservationPeriodStartDate(reservation.getReservationPeriod().startDate());
        entity.setReservationPeriodEndDate(reservation.getReservationPeriod().endDate());
        entity.setEquipmentBatchQuantity(reservation.getEquipmentBatch().quantity());
        entity.setEquipmentBatchUnit(reservation.getEquipmentBatch().unit());
        entity.setAccessLevel(reservation.getAccessLevel());
        entity.setStatus(reservation.getStatus());
        entity.setReservationNotes(reservation.getReservationNotes());
        return entity;
    }
    /**
     * Rebuilds a domain reservation from stored values.
     * @param entity persistence entity
     * @return validated aggregate
     */
    public static EquipmentReservation toDomain(EquipmentReservationPersistenceEntity entity) {
        return new EquipmentReservation(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(),
                new ReservationCode(entity.getReservationCode()), new LaboratoryId(UUID.fromString(entity.getLaboratoryId())),
                new ReservationPeriod(entity.getReservationPeriodStartDate(), entity.getReservationPeriodEndDate()),
                new EquipmentBatch(entity.getEquipmentBatchQuantity(), entity.getEquipmentBatchUnit()),
                entity.getAccessLevel(), entity.getStatus(), entity.getReservationNotes());
    }
}
