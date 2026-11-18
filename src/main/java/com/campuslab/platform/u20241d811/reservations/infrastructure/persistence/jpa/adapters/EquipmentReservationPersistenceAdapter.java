package com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.adapters;

import com.campuslab.platform.u20241d811.reservations.domain.exceptions.DuplicateReservationCodeException;
import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.ReservationCode;
import com.campuslab.platform.u20241d811.reservations.domain.repositories.EquipmentReservationRepository;
import com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.mappers.EquipmentReservationPersistenceMapper;
import com.campuslab.platform.u20241d811.reservations.infrastructure.persistence.jpa.repositories.EquipmentReservationJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import java.util.Locale;

/**
 * Implements the domain repository through Spring Data JPA.
 * @author rodrigoxd67
 */
@Repository
public class EquipmentReservationPersistenceAdapter implements EquipmentReservationRepository {
    private static final String CODE_CONSTRAINT = "uk_equipment_reservation_code";
    private final EquipmentReservationJpaRepository repository;

    /**
     * Injects the persistence repository.
     * @param repository JPA repository
     */
    public EquipmentReservationPersistenceAdapter(EquipmentReservationJpaRepository repository) {
        this.repository = repository;
    }

    /** {@inheritDoc} */
    @Override
    public boolean existsByReservationCode(ReservationCode code) {
        return repository.existsByReservationCode(code.code());
    }

    /** {@inheritDoc} */
    @Override
    public EquipmentReservation save(EquipmentReservation reservation) {
        try {
            var entity = EquipmentReservationPersistenceMapper.toEntity(reservation);
            return EquipmentReservationPersistenceMapper.toDomain(repository.saveAndFlush(entity));
        } catch (DataIntegrityViolationException exception) {
            if (isDuplicateCode(exception)) {
                throw new DuplicateReservationCodeException();
            }
            throw exception;
        }
    }

    private boolean isDuplicateCode(DataIntegrityViolationException exception) {
        Throwable cause = exception;
        while (cause != null) {
            if (cause instanceof ConstraintViolationException violation
                    && violation.getConstraintName() != null
                    && violation.getConstraintName().toLowerCase(Locale.ROOT).contains(CODE_CONSTRAINT)) {
                return true;
            }
            if (cause == cause.getCause()) break;
            cause = cause.getCause();
        }
        String message = exception.getMostSpecificCause().getMessage();
        return message != null && message.toLowerCase(Locale.ROOT).contains(CODE_CONSTRAINT);
    }
}
