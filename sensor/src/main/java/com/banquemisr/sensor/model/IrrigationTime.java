package com.banquemisr.sensor.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Table(name = "irrigation_times")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class IrrigationTime {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "plot_id", length = Integer.MAX_VALUE)
    private String plotId;

    @Column(name = "irrigated_at", length = Integer.MAX_VALUE)
    private String irrigatedAt;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
