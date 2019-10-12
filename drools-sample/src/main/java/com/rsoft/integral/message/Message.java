package com.rsoft.integral.message;

import java.io.Serializable;
import java.util.Map;

public interface Message extends Serializable {
    public long getTimestamp();

    public String getRequestNo();

    public String getEventCode();

    public String getUserCode();

    public Map<String, Object> getData();
}
