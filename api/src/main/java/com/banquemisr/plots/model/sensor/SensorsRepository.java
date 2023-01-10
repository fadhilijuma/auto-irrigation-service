package com.banquemisr.plots.model.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SensorsRepository extends JpaRepository<Sensor,Long> {
    Boolean existsSensorsBySensorId(String sensorId);
}
