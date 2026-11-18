package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Defines application failure types.
 * @author rodrigoxd67
 */
public enum FailureType {
    /** Invalid input or business rule. */
    VALIDATION,
    /** Conflict with an existing record. */
    CONFLICT,
    /** Unexpected internal failure. */
    UNEXPECTED
}
