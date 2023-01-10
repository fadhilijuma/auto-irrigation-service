package com.banquemisr.plots.controller;

import com.banquemisr.plots.exception.ResourceNotFoundException;
import com.banquemisr.plots.model.crop.Crop;
import com.banquemisr.plots.model.crop.CropRegistrationRequest;
import com.banquemisr.plots.model.crop.CropService;
import com.banquemisr.plots.model.plot.Plot;
import com.banquemisr.plots.model.plot.PlotRegistrationRequest;
import com.banquemisr.plots.model.plot.PlotService;
import com.banquemisr.plots.model.sensor.Sensor;
import com.banquemisr.plots.model.sensor.SensorRegistrationRequest;
import com.banquemisr.plots.model.sensor.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class IrrigationController {
    private final CropService cropService;
    private final SensorService sensorService;
    private final PlotService plotService;

    @Autowired
    public IrrigationController(CropService cropService, SensorService sensorService, PlotService plotService) {
        this.cropService = cropService;
        this.sensorService = sensorService;
        this.plotService = plotService;
    }

    // =================================================================================
    // Get paginated list of plots.
    @GetMapping("/plots")
    public Page<Plot> getAllPlots(@RequestParam int page, @RequestParam int size){
        log.info("get all plots with paging size {} and page {}", size,page);
        return plotService.findAll(page,size);
    }

    // =================================================================================
    // Register plot rest api.
    @PostMapping("/plots")
    public Plot createPlot(@RequestBody PlotRegistrationRequest plotRegistrationRequest) {
        log.info("new plot registration {}", plotRegistrationRequest);
        return plotService.registerPlot(plotRegistrationRequest);
    }

    // =================================================================================
    // Get plot by id rest api.
    @GetMapping("/plots/{id}")
    public ResponseEntity<Plot> getPlotById(@PathVariable Long id) {
        log.info("get plot by plot id {}", id);
        Plot plot = plotService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plot does not exist with id :" + id));
        return ResponseEntity.ok(plot);
    }

    // =================================================================================
    // Add crop to Plot.
    @PutMapping("/plots/{plotId}/crops/{cropId}")
    public ResponseEntity<?> addCropToPlot(@PathVariable(value = "plotId") Long plotId, @PathVariable(value = "cropId") Long cropId) {
        log.info("add crop to plot request with crop id {} and plot id {}", cropId, plotId);
        plotService.addCrop(plotId,cropId);
        return ResponseEntity.ok().build();
    }

    // =================================================================================
    // Add Sensor to Plot.
    @PutMapping("/plots/{plotId}/sensors/{sensorId}")
    public ResponseEntity<?> addSensorToPlot(@PathVariable(value = "plotId") Long plotId, @PathVariable(value = "sensorId") Long sensorId) {
        log.info("add sensor to plot request with sensor id {} and plot id {}", sensorId, plotId);
        plotService.addSensor(plotId,sensorId);
        return ResponseEntity.ok().build();
    }

    // =================================================================================
    // Register crop rest api.
    @PostMapping("/crops")
    public Crop createCrop(@RequestBody CropRegistrationRequest cropRegistrationRequest){
        log.info("new crop registration {}", cropRegistrationRequest);
        return cropService.registerCrop(cropRegistrationRequest);
    }

    // =================================================================================
    // Register sensor rest api.
    @PostMapping("/sensors")
    public Sensor createSensor(@RequestBody SensorRegistrationRequest sensorRegistrationRequest){
        log.info("new sensor registration {}", sensorRegistrationRequest);
        return sensorService.registerSensor(sensorRegistrationRequest);
    }
}
//{
//        "id": 33,
//        "plotNumber": "1333",
//        "acreage": "5",
//        "crop": {
//        "id": 34,
//        "idealMoistureContent": "50",
//        "cropType": "MAIZE"
//        },
//        "sensor": {
//        "id": 35,
//        "sensorId": "u2344"
//        },
//        "currentMoistureContent": "7",
//        "evapotranspiration": "9"
//        }