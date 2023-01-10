package com.banquemisr.plots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
//{
//    "idealMoistureContent": "50",
//    "cropType": "MAIZE"
//}

//{
//    "sensorId": "sensor1",
//    "sensorType": "IRRIGATION"
//}
//{
//    "plotNumber": "plot1",
//    "acreage": "90",
//    "currentMoistureContent": "90",
//    "evapotranspiration": "70"
//}
//localhost:8080/api/v1/plots?page=0&size=3