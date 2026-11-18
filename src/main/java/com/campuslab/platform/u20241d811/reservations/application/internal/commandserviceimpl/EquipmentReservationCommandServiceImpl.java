package com.campuslab.platform.u20241d811.reservations.application.internal.commandserviceimpl;

import com.campuslab.platform.u20241d811.reservations.application.commands.CreateEquipmentReservationCommand;
import com.campuslab.platform.u20241d811.reservations.application.commandservices.EquipmentReservationCommandService;
import com.campuslab.platform.u20241d811.reservations.domain.exceptions.DuplicateReservationCodeException;
import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects.*;
import com.campuslab.platform.u20241d811.reservations.domain.repositories.EquipmentReservationRepository;
import com.campuslab.platform.u20241d811.shared.application.results.FailureType;
import com.campuslab.platform.u20241d811.shared.application.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Validates and registers equipment reservations through the repository contract.
 * @author rodrigoxd67
 */
@Service
public class EquipmentReservationCommandServiceImpl implements EquipmentReservationCommandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentReservationCommandServiceImpl.class);
    private final EquipmentReservationRepository repository;

    /**
     * Injects the reservation repository.
     * @param repository reservation persistence contract
     */
    public EquipmentReservationCommandServiceImpl(EquipmentReservationRepository repository) {
        this.repository = repository;
    }

    /** {@inheritDoc} */
    @Override
    public Result<EquipmentReservation> handle(CreateEquipmentReservationCommand command) {
        if (command == null) {
            return Result.failure(FailureType.VALIDATION, "reservation.request.invalid");
        }
        EquipmentReservation reservation;
        try {
            var code = validate(() -> new ReservationCode(command.reservationCode()), "reservation.code.invalid");
            var laboratory = validate(() -> new LaboratoryId(parseUuid(command.laboratoryId())), "reservation.laboratory.invalid");
            var period = validate(() -> new ReservationPeriod(command.reservationPeriodStartDate(),
                    command.reservationPeriodEndDate()), "reservation.period.invalid");
            var batch = validate(() -> new EquipmentBatch(command.equipmentBatchQuantity(),
                    command.equipmentBatchUnit()), "reservation.batch.invalid");
            var status = validate(() -> parseStatus(command.status()), "reservation.status.invalid");
            reservation = validate(() -> new EquipmentReservation(code, laboratory, period, batch,
                    command.accessLevel(), status, command.reservationNotes()), "reservation.access.invalid");
        } catch (InvalidReservationException exception) {
            return Result.failure(FailureType.VALIDATION, exception.getMessage());
        }
        try {
            if (repository.existsByReservationCode(reservation.getReservationCode())) {
                return Result.failure(FailureType.CONFLICT, "reservation.code.duplicate");
            }
            return Result.success(repository.save(reservation));
        } catch (DuplicateReservationCodeException exception) {
            return Result.failure(FailureType.CONFLICT, "reservation.code.duplicate");
        } catch (RuntimeException exception) {
            LOGGER.error("Reservation registration failed: {}", exception.getClass().getSimpleName());
            return Result.failure(FailureType.UNEXPECTED, "reservation.registration.failed");
        }
    }

    private UUID parseUuid(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Laboratory identifier is required.");
        }
        UUID identifier = UUID.fromString(value);
        if (!identifier.toString().equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("Laboratory identifier must be a canonical UUID.");
        }
        return identifier;
    }

    private ReservationStatus parseStatus(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Reservation status must not be null.");
        }
        return ReservationStatus.valueOf(value);
    }

    private <T> T validate(Supplier<T> factory, String messageKey) {
        try {
            return factory.get();
        } catch (IllegalArgumentException exception) {
            throw new InvalidReservationException(messageKey);
        }
    }

    private static final class InvalidReservationException extends IllegalArgumentException {
        private InvalidReservationException(String messageKey) {
            super(messageKey);
        }
    }
}
