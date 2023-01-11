package com.banquemisr.plots.model.crop;

import com.banquemisr.plots.model.BaseEntity;
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
@ToString
@Table(name = "crops")
public class Crop extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "ideal_moisture_content")
    @Type(type = "org.hibernate.type.TextType")
    private String idealMoistureContent;

    @Column(name = "crop_type")
    @Type(type = "org.hibernate.type.TextType")
    private String cropType;
}
