package com.banquemisr.cronjobs.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlotsEngine {
    private final SlotsRepository slotsRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topic;

    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    @Async
    public void postIrrigationSlots() throws IOException {
        log.info("irrigation slots job started {}", Instant.now());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        List<Slots> slots = slotsRepository.findAll();

        for (Slots slot : slots) {
            String json = ow.writeValueAsString(slot);
            log.info("posting slot to Kafka : {}", json);
            kafkaTemplate.send(topic, json);
        }
    }
}
