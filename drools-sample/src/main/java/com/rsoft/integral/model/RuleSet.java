package com.rsoft.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Rule.
 * 
 * @author rsoft
 *
 */
@Data
@NoArgsConstructor
public class RuleSet implements Serializable {
    private static final long serialVersionUID = 1L;
    private String source;
    private String rulekey;
    private String ruledesc;
    private String ruletype; // drl,dsl,xlsx
    private String content;
    private Integer priority;

    private Date effective;
    private Date expires;
    private boolean enabled;
    // 仅为展示说明用
    private int calctype;
    private Integer score; // 分值或比例

    private int version;
    private Date modifiedTime;
    private Date createTime;
}
