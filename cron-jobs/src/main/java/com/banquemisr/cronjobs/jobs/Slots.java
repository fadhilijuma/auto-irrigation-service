package com.banquemisr.cronjobs.jobs;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Immutable
@Entity
@jakarta.persistence.Table(name = "slots_view")
@Getter
@Setter
@NoArgsConstructor
public class Slots {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @jakarta.persistence.Column(name = "plot_id")
    private Long plotId;

    @jakarta.persistence.Column(name = "sensor_id")
    private Long sensorId;

    @jakarta.persistence.Column(name = "volume")
    private Integer volume;
}