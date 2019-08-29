package com.rsoft.integral.dao;

import com.rsoft.integral.model.EventSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * RuleDao.
 * 
 * @author rsoft
 *
 */
@Mapper
public interface EventSourceDao {
    @Select("select * from itg_event_source where enabled=1")
    List<EventSource> findEnabled();

    @Select("select * from itg_event_source")
    List<EventSource> findAll();
}
