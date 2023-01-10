package com.banquemisr.plots.model.crop;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CropService {
    private final CropsRepository cropsRepository;

    public Crop registerCrop(CropRegistrationRequest request) {
        Crop crop = Crop.builder()
                .idealMoistureContent(request.idealMoistureContent())
                .cropType(request.cropType())
                .build();

        Boolean exists = cropsRepository.existsCropsByCropType(request.cropType());
        if (!exists) {
            cropsRepository.saveAndFlush(crop);
        }
        return crop;
    }
}
