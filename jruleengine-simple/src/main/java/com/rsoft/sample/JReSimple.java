package com.rsoft.sample;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

public class JReSimple {

    public void testJRuleEngine() {
        try {
            // 加载并自动注册规则服务提供者实现
            Class.forName("org.jruleengine.RuleServiceProviderImpl");
            RuleServiceProvider serviceProvider = RuleServiceProviderManager.getRuleServiceProvider("org.jruleengine");
            // 获取规则管理服务
            RuleAdministrator adm = serviceProvider.getRuleAdministrator();
            // new FileInputStream("simple1.xml");
            InputStream is = JReSimple.class.getResourceAsStream("/rules/simple1.xml");
            System.out.println("Acquired InputStream to simple1.xml: " + is);

            RuleExecutionSet res1 = adm.getLocalRuleExecutionSetProvider(null).createRuleExecutionSet(is,
                    null);
            is.close();
            System.out.println("Loaded RuleExecutionSet: " + res1);
            // 注册规则集
            String uri = res1.getName();
            adm.registerRuleExecutionSet(uri, res1, null);
            // 获取规则引擎运行时
            RuleRuntime rr = serviceProvider.getRuleRuntime();

            // StatefulRuleSession ruleSession = (StatefulRuleSession) rr.createRuleSession(uri, new HashMap(),
            // RuleRuntime.STATEFUL_SESSION_TYPE);
            // 创建无状态会话
            StatelessRuleSession ruleSession = (StatelessRuleSession) rr.createRuleSession(uri, new HashMap(),
                    RuleRuntime.STATELESS_SESSION_TYPE);

            // 加入事实对象
            List input = new ArrayList();
            Customer c = Customer.builder().name("zhangsan").location("beijing").age(32).build();
            input.add(c);
            Message msg = Message.builder().message("hello,world").build();
            input.add(msg);

            // 执行规则
            List results = ruleSession.executeRules(input);
            // 关闭会话
            ruleSession.release();

        } catch (NoClassDefFoundError e) {
            if (e.getMessage().indexOf("Exception") != -1) {
                System.err.println("Error: The Rule Engine Implementation could not be found.");
            } else {
                System.err.println("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
