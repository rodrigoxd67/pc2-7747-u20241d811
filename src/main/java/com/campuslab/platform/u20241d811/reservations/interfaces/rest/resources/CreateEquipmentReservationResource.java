package com.campuslab.platform.u20241d811.reservations.interfaces.rest.resources;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Contains the equipment reservation request fields.
 * @author rodrigoxd67
 */
@Getter
@Setter
public class CreateEquipmentReservationResource {
    @NotBlank(message = "{reservation.code.invalid}")
    private String reservationCode;
    @NotBlank(message = "{reservation.laboratory.invalid}")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "{reservation.laboratory.invalid}")
    private String laboratoryId;
    @NotNull(message = "{reservation.period.invalid}")
    private LocalDate reservationPeriodStartDate;
    @NotNull(message = "{reservation.period.invalid}")
    private LocalDate reservationPeriodEndDate;
    @NotNull(message = "{reservation.batch.invalid}")
    @Positive(message = "{reservation.batch.invalid}")
    private Integer equipmentBatchQuantity;
    @NotBlank(message = "{reservation.batch.invalid}")
    private String equipmentBatchUnit;
    @NotNull(message = "{reservation.access.invalid}")
    @Min(value = 1, message = "{reservation.access.invalid}")
    @Max(value = 5, message = "{reservation.access.invalid}")
    private Integer accessLevel;
    @NotNull(message = "{reservation.status.invalid}")
    @Pattern(regexp = "PENDING|CONFIRMED|COMPLETED|CANCELLED", message = "{reservation.status.invalid}")
    private String status = "PENDING";
    private String reservationNotes;
}
