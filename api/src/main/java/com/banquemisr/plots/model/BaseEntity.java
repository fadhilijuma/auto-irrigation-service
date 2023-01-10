package com.banquemisr.plots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @Column(name = "created_at",updatable = false)
    @JsonIgnore
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @JsonIgnore
    private Timestamp updatedAt;

    @PrePersist
    public void onInsert() {
        createdAt = Timestamp.from(ZonedDateTime.now(ZoneId.of("Africa/Cairo")).toInstant());
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = Timestamp.from(ZonedDateTime.now(ZoneId.of("Africa/Cairo")).toInstant());
    }
}
