package com.banquemisr.plots.model.sensor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorsRepository sensorsRepository;

    public Sensor registerSensor(SensorRegistrationRequest sensorRegistrationRequest) {
        Sensor sensor = Sensor.builder()
                .sensorId(sensorRegistrationRequest.sensorId())
                .sensorType(sensorRegistrationRequest.sensorType())
                .build();

        Boolean exists = sensorsRepository.existsSensorsBySensorId(sensorRegistrationRequest.sensorId());
        if (!exists) {
            sensorsRepository.saveAndFlush(sensor);
        }
        return sensor;
    }
}
