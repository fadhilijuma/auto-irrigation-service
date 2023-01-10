package com.banquemisr.plots.model.sensor;

import com.banquemisr.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensors")
@ToString
public class Sensor extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "sensor_id")
    @Type(type = "org.hibernate.type.TextType")
    private String sensorId;

    @Column(name = "sensor_type")
    @Type(type = "org.hibernate.type.TextType")
    private String sensorType;
}
