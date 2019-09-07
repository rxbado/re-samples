package com.rsoft.samples.msg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class EvtMessageReceiver {
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "#{'${spring.kafka.topics.test}'.split(',')}", groupId = "test--1")
    public void listenGroup1(ConsumerRecord<?, ?> rec) {

        Optional<?> msg = Optional.ofNullable(rec.value());
        if (msg.isPresent()) {
            EvtMessage em = gson.fromJson((String) msg.get(), EvtMessage.class);
            log.info("------------------ message =" + em);
        }
    }

    @KafkaListener(topics = { "#{'${spring.kafka.topics.test}'.split(',')}" }, groupId = "test--2")
    public void listenGroup2(ConsumerRecord<?, ?> rec) {

        Optional<?> msg = Optional.ofNullable(rec.value());
        if (msg.isPresent()) {
            EvtMessage em = gson.fromJson((String) msg.get(), EvtMessage.class);
            log.info("------------------ message-g2 =" + em);
        }
    }

    @Data
    public class EvtMessage<T> {
        private long timestamp;
        private String requestNo;
        private String eventCode;
        T data;
    }
}
