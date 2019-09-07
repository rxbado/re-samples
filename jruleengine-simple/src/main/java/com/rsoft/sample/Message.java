package com.rsoft.sample;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String message;
}
