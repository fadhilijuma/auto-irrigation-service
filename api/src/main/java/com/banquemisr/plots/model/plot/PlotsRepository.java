package com.banquemisr.plots.model.plot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotsRepository extends JpaRepository<Plot, Long> {
}