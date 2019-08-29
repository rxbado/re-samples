package com.rsoft.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsSimpleTest {
    public static void main(String[] args) throws Exception {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession session = kc.newKieSession("ksession-simple");
        session.insert("Tom and Jerry");
        session.fireAllRules();

        session.dispose();
    }
}
