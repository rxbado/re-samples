package com.rsoft.integral.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsoft.integral.consts.IntegralConsts;
import com.rsoft.integral.dao.EventDao;
import com.rsoft.integral.model.Event;
import com.rsoft.integral.rule.EventFact;
import com.rsoft.ruleengine.RuleExecutor;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class EventMessageReceiver {
    @Autowired
    EventDao eventDao;
    private Gson gson = new GsonBuilder().create();

    @Autowired
    private RuleExecutor ruleExecutor;

    @KafkaListener(topics = { IntegralConsts.INTEGRAL_EVENT_TOPIC, "test-topic" })
    public void listen(ConsumerRecord<?, ?> rec) {

        Optional<?> msg = Optional.ofNullable(rec.value());
        if (msg.isPresent()) {
            EventMessage em = gson.fromJson((String) msg.get(), EventMessage.class);

            Event event = Event.builder().userCode(em.getUserCode())//
                    .fired(false)//
                    .eventCode(em.getEventCode())//
                    .eventid(em.getRequestNo())//
                    .data(gson.toJson(em.getData()))//
                    .message(gson.toJson(em)).build();

            int firedRuleCount = ruleExecutor.execute(event.getEventCode(), (EventFact) event);

            eventDao.insert(event);

            // log.info("----------------- record =" + rec);
            // log.info("------------------ message =" + em);
        }
    }
}
