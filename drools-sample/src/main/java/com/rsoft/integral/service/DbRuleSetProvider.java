package com.rsoft.integral.service;

import com.rsoft.integral.dao.EventSourceDao;
import com.rsoft.integral.dao.RuleSetDao;
import com.rsoft.integral.model.EventSource;
import com.rsoft.integral.model.RuleSet;
import com.rsoft.ruleengine.Rule;
import com.rsoft.ruleengine.RuleSetProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class DbRuleSetProvider implements RuleSetProvider {
    @Autowired
    private RuleSetDao ruleSetDao;
    @Autowired
    private EventSourceDao eventSourceDao;

    public List<Rule> getRuleSetByScene(String scene) {
        Map<String, List<Rule>> ruleinfos = getRuleSet();
        return ruleinfos.get(scene);
    }

    public Map<String, List<Rule>> getRuleSet() {
        Map<String, List<Rule>> ruleinfos = new HashMap<>();
        // load all event source
        List<EventSource> sources = eventSourceDao.findAll();
        for (EventSource es : sources) {
            List<Rule> ruleinfosByScene = new ArrayList<Rule>();
            List<RuleSet> rulesets = ruleSetDao.findRuleSetBySource(es.getCode());
            if (rulesets != null && rulesets.size() > 0) {
                for (RuleSet rs : rulesets) {
                    Rule ri = new Rule();
                    ri.setRulekey(rs.getRulekey());
                    ri.setContent(rs.getContent());
                    ri.setPriority(rs.getPriority());
                    ri.setEffective(rs.getEffective());
                    ri.setExpires(rs.getExpires());
                    ri.setScene(es.getCode());//

                    ruleinfosByScene.add(ri);
                }
                ruleinfos.put(es.getCode(), ruleinfosByScene);
            }
        }

        return ruleinfos;
    }

}
