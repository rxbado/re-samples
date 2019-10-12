package com.rsoft.integral.dao;

import com.rsoft.integral.model.Event;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventDao {
    @Insert("insert into itg_event(event_code,eventid,user_code,fired,message,data,score) "
            + "values(#{eventCode},#{eventid},#{userCode},#{fired},#{message},#{data},#{score})")
    int insert(Event e);
}
