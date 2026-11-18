package com.campuslab.platform.u20241d811.shared.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

/**
 * Stores generated identity and aggregate audit timestamps.
 * @author rodrigoxd67
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditableAbstractPersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
