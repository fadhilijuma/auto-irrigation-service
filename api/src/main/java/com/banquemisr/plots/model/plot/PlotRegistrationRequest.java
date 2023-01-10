package com.banquemisr.plots.model.plot;

public record PlotRegistrationRequest(
        String plotNumber,
        String acreage,
        String currentMoistureContent,
        String evapotranspiration) {
}
