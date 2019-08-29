package com.rsoft.samples.web;

import com.rsoft.integral.dao.EventSourceDao;
import com.rsoft.integral.message.EventMessage;
import com.rsoft.integral.message.EventMessageSender;
import com.rsoft.integral.model.EventSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/msg")
public class EventMessageController {
    @Autowired
    private EventMessageSender sender;
    @Autowired
    private EventSourceDao sourceDao;

    @PostMapping("/sender")
    public String sendEventMessage(@RequestBody EventMessage em) throws Exception {
        List<EventSource> ess = sourceDao.findEnabled();

        Map<String, Object> attrs = new HashMap<String, Object>();
        attrs.put("productType", (new Random()).nextInt(3));
        attrs.put("amount", (new Random()).nextInt(99) * 1000);
        // 连续登录次数
        attrs.put("clc", (new Random()).nextInt(28));

        EventMessage message = EventMessage.builder()//
                .timestamp(System.currentTimeMillis())//
                .userCode(String.format("U%s", (new Random()).nextInt(999999)))//
                .eventCode(ess.get((new Random()).nextInt(6)).getCode())//
                .requestNo(UUID.randomUUID().toString())//
                .data(attrs)//
                .build();

        SendResult<String, String> results = sender.send(message);

        return "ok";
    }
}
