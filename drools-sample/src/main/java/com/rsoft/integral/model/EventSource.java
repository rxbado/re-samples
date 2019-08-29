package com.rsoft.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Event.
 * 
 * @author rsoft
 *
 */
@Data
@NoArgsConstructor
public class EventSource implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String desc;

    private Boolean enabled;
}
