package com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects;

import java.util.UUID;

/**
 * References a laboratory managed by another bounded context.
 * @param value laboratory UUID
 * @author rodrigoxd67
 */
public record LaboratoryId(UUID value) {
    /**
     * Validates the laboratory identifier.
     * @param value required UUID
     * @throws IllegalArgumentException if value is null
     */
    public LaboratoryId {
        if (value == null) {
            throw new IllegalArgumentException("Laboratory identifier must not be null.");
        }
    }
}
