package com.banquemisr.plots.model.plot;

import com.banquemisr.model.crop.Crop;
import com.banquemisr.model.irrigation.IrrigationSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlotsRepository extends JpaRepository<Plot, Long> {
}