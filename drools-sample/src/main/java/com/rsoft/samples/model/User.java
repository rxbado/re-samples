package com.rsoft.samples.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user.
 * 
 * @author bado
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userid;
    private String username;
    private int gender;
    private int level;

    private int totalScore;
}
