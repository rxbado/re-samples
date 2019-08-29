package com.rsoft.integral.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsoft.integral.consts.IntegralConsts;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventMessageSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    // 发送消息方法
    public SendResult<String, String> send(EventMessage em) throws Exception {
        // log.info("+++++++++++++++++++++ message = {}", gson.toJson(em));
        return kafkaTemplate.send(IntegralConsts.INTEGRAL_EVENT_TOPIC, gson.toJson(em)).get();
    }
}
