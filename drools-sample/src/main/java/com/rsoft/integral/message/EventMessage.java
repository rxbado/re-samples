package com.rsoft.integral.message;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EventMessage implements Message {
    private static final long serialVersionUID = 1L;
    private long timestamp;
    private String requestNo;

    private String eventCode;
    private String userCode;
    private Map<String, Object> data;
}
