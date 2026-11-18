package com.campuslab.platform.u20241d811.reservations.interfaces.rest;

import com.campuslab.platform.u20241d811.reservations.application.commandservices.EquipmentReservationCommandService;
import com.campuslab.platform.u20241d811.reservations.domain.model.aggregates.EquipmentReservation;
import com.campuslab.platform.u20241d811.reservations.interfaces.rest.resources.CreateEquipmentReservationResource;
import com.campuslab.platform.u20241d811.reservations.interfaces.rest.transform.*;
import com.campuslab.platform.u20241d811.shared.application.results.*;
import com.campuslab.platform.u20241d811.shared.interfaces.rest.transform.ErrorResponseFactory;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

/**
 * Exposes equipment reservation registration.
 * @author rodrigoxd67
 */
@RestController
@RequestMapping("/api/v1/equipment-reservations")
public class EquipmentReservationsController {
    private final EquipmentReservationCommandService service;
    private final ErrorResponseFactory errors;
    /**
     * Injects registration and error response services.
     * @param service registration use case
     * @param errors localized error factory
     */
    public EquipmentReservationsController(EquipmentReservationCommandService service, ErrorResponseFactory errors) {
        this.service = service;
        this.errors = errors;
    }
    /**
     * Registers an equipment reservation.
     * @param resource validated request body
     * @param locale response language
     * @return created resource or localized error
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateEquipmentReservationResource resource, Locale locale) {
        var result = service.handle(CreateEquipmentReservationCommandFromResourceAssembler.toCommandFromResource(resource));
        return switch (result) {
            case Success<EquipmentReservation> success -> ResponseEntity.status(HttpStatus.CREATED)
                    .header(HttpHeaders.CONTENT_LANGUAGE, locale.toLanguageTag())
                    .body(EquipmentReservationResourceFromEntityAssembler.toResourceFromEntity(success.value()));
            case Failure<EquipmentReservation> failure -> errors.create(switch (failure.type()) {
                case VALIDATION -> HttpStatus.BAD_REQUEST;
                case CONFLICT -> HttpStatus.CONFLICT;
                case UNEXPECTED -> HttpStatus.INTERNAL_SERVER_ERROR;
            }, failure.messageKey(), locale);
        };
    }
}
