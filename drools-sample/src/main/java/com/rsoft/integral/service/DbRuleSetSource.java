package com.rsoft.integral.service;

import com.rsoft.integral.dao.EventSourceDao;
import com.rsoft.integral.dao.RuleSetDao;
import com.rsoft.integral.model.EventSource;
import com.rsoft.integral.model.RuleSet;
import com.rsoft.ruleengine.RuleInfo;
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
public class DbRuleSetSource implements RuleSetProvider {
    @Autowired
    private RuleSetDao ruleSetDao;
    @Autowired
    private EventSourceDao eventSourceDao;

    public List<RuleInfo> getRuleSetByScene(String scene) {
        Map<String, List<RuleInfo>> ruleinfos = getRuleSetAsMap();
        return ruleinfos.get(scene);
    }

    public Map<String, List<RuleInfo>> getRuleSetAsMap() {
        Map<String, List<RuleInfo>> ruleinfos = new HashMap<>();
        // load all event source
        List<EventSource> sources = eventSourceDao.findAll();
        for (EventSource es : sources) {
            List<RuleInfo> ruleinfosByScene = new ArrayList<RuleInfo>();
            List<RuleSet> rulesets = ruleSetDao.findRuleSetBySource(es.getCode());
            if (rulesets != null && rulesets.size() > 0) {
                for (RuleSet rs : rulesets) {
                    RuleInfo ri = new RuleInfo();
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
