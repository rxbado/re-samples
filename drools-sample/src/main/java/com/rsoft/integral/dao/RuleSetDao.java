package com.rsoft.integral.dao;

import com.rsoft.integral.model.RuleSet;

import org.apache.ibatis.annotations.Insert;
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
public interface RuleSetDao {
    RuleSet findByRuleKey(String ruleid);

    @Select("select * from itg_rule_set where enabled=1")
    List<RuleSet> findEnabled();

    @Select("select * from itg_rule_set where enabled=1 and rulekey in(select distinct rulekey from itg_rule_event_source where event_code=#{source}) ")
    List<RuleSet> findRuleSetBySource(String source);

    @Insert("insert into itg_rule_set(ruleid,ruledesc,ruletype,content,priority,effective,expires,calctype,score,version,create_time,modified_time) "
            + "values(#{ruleid},#{ruledesc},#{ruletype},#{content},#{priority},#{effective},#{expires},#{calctype},#{score},#{version},#{createTime},#{modifiedTime})")
    int insertRule(RuleSet rule);
}
