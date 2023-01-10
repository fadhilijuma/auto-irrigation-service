package com.banquemisr.sensor.consumer;

import com.banquemisr.sensor.model.IrrigationRepository;
import com.banquemisr.sensor.model.IrrigationTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class IrrigationConsumer {

    @Value("${message.topic.name")
    private String topicName;

    private final IrrigationRepository irrigationRepository;

    @KafkaListener(topics = "${message.topic.name}", groupId = "${kafka.consumer.groupid")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicName);
        log.info("Slot: {}", payload.value());
        Gson gson = new GsonBuilder().create();
        Map jsonMap = gson.fromJson(payload.value(), Map.class);
        IrrigationTime irrigationTime = new IrrigationTime();
        irrigationTime.setPlotId(jsonMap.get("plotId").toString());
        irrigationTime.setIrrigatedAt(Instant.now().toString());
        irrigationRepository.save(irrigationTime);
    }

}
