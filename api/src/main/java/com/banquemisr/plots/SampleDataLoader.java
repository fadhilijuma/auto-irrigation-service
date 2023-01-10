package com.banquemisr.plots;

import com.banquemisr.plots.model.crop.Crop;
import com.banquemisr.plots.model.crop.CropsRepository;
import com.banquemisr.plots.model.plot.Plot;
import com.banquemisr.plots.model.plot.PlotsRepository;
import com.banquemisr.plots.model.sensor.Sensor;
import com.banquemisr.plots.model.sensor.SensorType;
import com.banquemisr.plots.model.sensor.SensorsRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private final Faker faker;
    private final CropsRepository cropsRepository;
    private final SensorsRepository sensorsRepository;
    private final PlotsRepository plotsRepository;

    public SampleDataLoader(Faker faker, CropsRepository cropsRepository, SensorsRepository sensorsRepository, PlotsRepository plotsRepository) {
        this.faker = faker;
        this.cropsRepository = cropsRepository;
        this.sensorsRepository = sensorsRepository;
        this.plotsRepository = plotsRepository;
    }

    @Override
    public void run(String... args) {
        List<Crop> crops = IntStream.rangeClosed(1,5)
                .mapToObj(obj->{
                    Crop crop = new Crop();
                    crop.setIdealMoistureContent(String.valueOf(faker.number().numberBetween(10,40)));
                    crop.setCropType(faker.funnyName().name());
                    return crop;
                }).toList();
        cropsRepository.saveAll(crops);

        List<Sensor> sensors = IntStream.rangeClosed(1,5)
                .mapToObj(obj->{
                    Sensor sensor = new Sensor();
                    sensor.setSensorId(faker.funnyName().name());
                    sensor.setSensorType(SensorType.IRRIGATION.name());
                    return sensor;
                }).toList();
        sensorsRepository.saveAll(sensors);

        List<Plot> plots = IntStream.rangeClosed(1,5)
                .mapToObj(obj->{
                    Plot plot = new Plot();
                    plot.setPlotNumber(faker.funnyName().name() +"/"+ faker.number().numberBetween(10, 40));
                    plot.setAcreage(String.valueOf(faker.number().numberBetween(10, 40)));
                    plot.setEvapotranspiration(String.valueOf(faker.number().numberBetween(10, 40)));
                    plot.setCurrentMoistureContent(String.valueOf(faker.number().numberBetween(10, 40)));
                    return plot;
                }).toList();
        plotsRepository.saveAll(plots);

        sensors.forEach(sensor -> plots.forEach(plot -> {
                plot.setSensor(sensor);
                plotsRepository.save(plot);
            }));
        crops.forEach(crop -> plots.forEach(plot -> {
            plot.setCrop(crop);
            plotsRepository.save(plot);
        }));

    }
}
