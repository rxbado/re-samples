package com.rsoft.integral.rule;

import java.io.Serializable;
import java.util.Map;

public interface EventFact extends Serializable {

    public String getEventCode();

    public String getEventid();

    public String getUserCode();

    public Integer getScore();

    public Map<String, Object> getAttrs();

    public void setScore(Integer score);
}
