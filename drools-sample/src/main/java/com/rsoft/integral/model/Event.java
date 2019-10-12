package com.rsoft.integral.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsoft.integral.rule.EventFact;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class Event implements EventFact, Serializable {

    private static final long serialVersionUID = 1L;
    private String eventCode;
    private String eventid;
    private String userCode;
    private boolean fired;
    private String message;
    private String data;
    private Integer score;

    @Override
    public Map<String, Object> getAttrs() {
        Gson gson = new GsonBuilder().create(); // 性能开销
        return gson.fromJson(this.data, Map.class);
    }

}
