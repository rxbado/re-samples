package com.rsoft.drools;

import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;
import org.kie.internal.io.ResourceFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DecitionTableTest {

    @Test
    public void decisionTableTest() {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String rules = compiler.compile(
                ResourceFactory.newClassPathResource("rules" + File.separator + "continue-login.xlsx", "UTF-8"),
                "Sheet1");
        System.out.println(rules);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/rules/test-rule-excel.drl"));
            out.write(rules);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
