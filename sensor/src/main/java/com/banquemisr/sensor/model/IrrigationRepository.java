package com.banquemisr.sensor.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationRepository extends JpaRepository<IrrigationTime, Long> {
}
