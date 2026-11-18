package com.campuslab.platform.u20241d811.reservations.domain.model.valueobjects;

/**
 * Represents a positive equipment quantity and its unit.
 * @param quantity number of equipment units
 * @param unit unit label
 * @author rodrigoxd67
 */
public record EquipmentBatch(Integer quantity, String unit) {
    /**
     * Validates the quantity and unit.
     * @param quantity required positive quantity
     * @param unit required nonblank unit
     * @throws IllegalArgumentException if quantity or unit is invalid
     */
    public EquipmentBatch {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Equipment quantity must be greater than zero.");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Equipment unit must not be null or blank.");
        }
    }

    /**
     * Returns an increased batch without modifying this batch.
     * @param additionalQuantity positive number of units to add
     * @return new batch with the same unit
     * @throws IllegalArgumentException if the increment is invalid or the sum overflows
     */
    public EquipmentBatch addUnits(Integer additionalQuantity) {
        if (additionalQuantity == null || additionalQuantity <= 0) {
            throw new IllegalArgumentException("Additional quantity must be greater than zero.");
        }
        try {
            return new EquipmentBatch(Math.addExact(quantity, additionalQuantity), unit);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("Equipment quantity must not exceed Integer.MAX_VALUE.", exception);
        }
    }
}
