package com.campuslab.platform.u20241d811.shared.application.results;

/**
 * Classifies application failures independently of the delivery protocol.
 * @author rodrigoxd67
 */
public enum FailureType {
    /** Input or business-rule validation failed. */
    VALIDATION,
    /** An operation conflicts with an existing business record. */
    CONFLICT,
    /** An unexpected internal failure prevented the operation. */
    UNEXPECTED
}
