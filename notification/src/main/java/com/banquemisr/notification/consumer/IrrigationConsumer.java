package com.banquemisr.notification.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IrrigationConsumer {

    @Value("${message.topic.name")
    private String topicName;
    @KafkaListener(topics = "${message.topic.name}", groupId = "${kafka.consumer.groupid")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicName);
        log.info("notificatio: {}", payload.value());
    }

}
