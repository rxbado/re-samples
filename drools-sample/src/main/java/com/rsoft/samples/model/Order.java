package com.rsoft.samples.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * order.
 * 
 * @author rsoft
 *
 */
@Data
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date bookingDate;
    private int amout;
    private User user;
    private int score;
}
