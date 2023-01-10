package com.banquemisr.plots.model.plot;

import com.banquemisr.plots.model.crop.Crop;
import com.banquemisr.plots.model.crop.CropsRepository;
import com.banquemisr.plots.model.sensor.Sensor;
import com.banquemisr.plots.model.sensor.SensorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlotService {
    private final PlotsRepository plotsRepository;
    private final CropsRepository cropsRepository;
    private final SensorsRepository sensorsRepository;

    public Plot registerPlot(PlotRegistrationRequest plotRegistrationRequest) {
        Plot plot = Plot.builder()
                .plotNumber(plotRegistrationRequest.plotNumber())
                .acreage(plotRegistrationRequest.acreage())
                .currentMoistureContent(plotRegistrationRequest.currentMoistureContent())
                .evapotranspiration(plotRegistrationRequest.evapotranspiration())
                .build();
        plotsRepository.saveAndFlush(plot);
        return plot;

    }

    public Optional<Plot> findById(Long id) {
        return plotsRepository.findById(id);
    }
    public Page<Plot> findAll(int page, int size) {
        return plotsRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<Plot> save(Long id) {
        return plotsRepository.findById(id);
    }

    public void addCrop(Long plotId, Long cropId) {
        Plot plot = plotsRepository.getReferenceById(plotId);
        Crop crop = cropsRepository.getReferenceById(cropId);
        plot.setCrop(crop);
        plotsRepository.save(plot);
    }

    public void addSensor(Long plotId, Long sensorId) {
        Plot plot = plotsRepository.getReferenceById(plotId);
        Sensor sensor = sensorsRepository.getReferenceById(sensorId);
        plot.setSensor(sensor);
        plotsRepository.save(plot);
    }
}
