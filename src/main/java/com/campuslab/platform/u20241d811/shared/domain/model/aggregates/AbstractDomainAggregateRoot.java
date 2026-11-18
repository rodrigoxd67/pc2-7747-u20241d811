package com.campuslab.platform.u20241d811.shared.domain.model.aggregates;

import java.time.Instant;

/**
 * Provides aggregate identity and audit timestamps.
 * @author rodrigoxd67
 */
public abstract class AbstractDomainAggregateRoot {
    private final Long id;
    private final Instant createdAt;
    private final Instant updatedAt;

    /** Creates an aggregate with both timestamps set to the same instant. */
    protected AbstractDomainAggregateRoot() {
        this(null, Instant.now());
    }

    private AbstractDomainAggregateRoot(Long id, Instant timestamp) {
        this(id, timestamp, timestamp);
    }

    /**
     * Restores aggregate identity and audit timestamps.
     * @param id persistence identity, null before persistence
     * @param createdAt creation instant
     * @param updatedAt last update instant
     * @throws IllegalArgumentException if timestamps are missing or inconsistent
     */
    protected AbstractDomainAggregateRoot(Long id, Instant createdAt, Instant updatedAt) {
        if (createdAt == null || updatedAt == null) {
            throw new IllegalArgumentException("Audit timestamps are required.");
        }
        if (updatedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException("Update timestamp must not precede creation timestamp.");
        }
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the persistence identity.
     * @return identity, or null before persistence
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the creation instant.
     * @return creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the last update instant.
     * @return update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
