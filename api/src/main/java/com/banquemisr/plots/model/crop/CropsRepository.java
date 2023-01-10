package com.banquemisr.plots.model.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CropsRepository extends JpaRepository<Crop,Long> {
    Boolean existsCropsByCropType(String cropType);
}
