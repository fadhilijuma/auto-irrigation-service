package com.banquemisr.plots.controller;

import com.banquemisr.plots.model.crop.Crop;
import com.banquemisr.plots.model.crop.CropRegistrationRequest;
import com.banquemisr.plots.model.crop.CropService;
import com.banquemisr.plots.model.plot.Plot;
import com.banquemisr.plots.model.plot.PlotRegistrationRequest;
import com.banquemisr.plots.model.plot.PlotService;
import com.banquemisr.plots.model.sensor.Sensor;
import com.banquemisr.plots.model.sensor.SensorRegistrationRequest;
import com.banquemisr.plots.model.sensor.SensorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IrrigationController.class)
class IrrigationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlotService plotService;
    @MockBean
    CropService cropService;
    @MockBean
    SensorService sensorService;
    @Test
    void getAllPlots() throws Exception {
        // Mock data returned.

        Plot plot = Plot.builder()
                .evapotranspiration("30")
                .plotNumber("plot123")
                .acreage("40")
                .currentMoistureContent("40")
                .build();

        List<Plot> plots = new ArrayList<>();
        plots.add(plot);

        Page<Plot> pagedResponse = new PageImpl<>(plots);

        Mockito.when(plotService.findAll(anyInt(),anyInt())).thenReturn(pagedResponse);

        // Create a mock http request.

        mockMvc.perform(get("/api/v1/plots?page=0&size=3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].plotNumber").value("plot123"));
    }

    @Test
    void createPlot() throws Exception {
        // Mock plot data to save.

        PlotRegistrationRequest plotRegistrationRequest = new PlotRegistrationRequest(
                "plot123","40","40","30"
        );

        Plot plot = Plot.builder()
                .id(1L)
                .evapotranspiration("30")
                .plotNumber("plot123")
                .acreage("40")
                .currentMoistureContent("40")
                .build();

        Mockito.when(plotService.registerPlot(any(PlotRegistrationRequest.class))).thenReturn(plot);

        // Mock post plot

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/plots")
                        .content(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsString(plotRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void getPlotById() throws Exception {
        // Mock plot data to save.

        Plot plot = Plot.builder()
                .id(1L)
                .evapotranspiration("30")
                .plotNumber("plot123")
                .acreage("40")
                .currentMoistureContent("40")
                .build();

        Mockito.when(plotService.findById(any(Long.class))).thenReturn(Optional.of(plot));
        // Create a mock http request.

        mockMvc.perform(get("/api/v1/plots/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createCrop() throws Exception {
        // Mock crop data to save.

        CropRegistrationRequest cropRegistrationRequest = new CropRegistrationRequest(
                "30","MAIZE"
        );

        Crop crop = Crop.builder()
                .id(1L)
                .cropType("MAIZE")
                .idealMoistureContent("30")
                .build();

        Mockito.when(cropService.registerCrop(any(CropRegistrationRequest.class))).thenReturn(crop);

        // Mock post crop

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/crops")
                        .content(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsString(cropRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createSensor() throws Exception {
        // Mock sensor data to save.

        SensorRegistrationRequest sensorRegistrationRequest = new SensorRegistrationRequest(
                "sensor123","IRRIGATION"
        );

        Sensor sensor = Sensor.builder()
                .id(1L)
                .sensorId("sensor123")
                .sensorType("IRRIGATION")
                .build();

        Mockito.when(sensorService.registerSensor(any(SensorRegistrationRequest.class))).thenReturn(sensor);

        // Mock sensor post

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/crops")
                        .content(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                                .writeValueAsString(sensorRegistrationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}