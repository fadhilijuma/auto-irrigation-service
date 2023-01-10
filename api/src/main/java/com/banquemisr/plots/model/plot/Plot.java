package com.banquemisr.plots.model.plot;

import com.banquemisr.plots.model.BaseEntity;
import com.banquemisr.plots.model.crop.Crop;
import com.banquemisr.plots.model.sensor.Sensor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "plots")
@ToString
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" })
public class Plot extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "plot_number")
    @Type(type = "org.hibernate.type.TextType")
    private String plotNumber;

    @Column(name = "acreage")
    @Type(type = "org.hibernate.type.TextType")
    private String acreage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_id", referencedColumnName = "id")
    private Crop crop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "current_moisture_content")
    @Type(type = "org.hibernate.type.TextType")
    private String currentMoistureContent;

    @Column(name = "evapotranspiration")
    @Type(type = "org.hibernate.type.TextType")
    private String evapotranspiration;

}