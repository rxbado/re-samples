package com.rsoft.samples.web;

import com.rsoft.ruleengine.RuleRunner;
import com.rsoft.ruleengine.RuleLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规则测试
 */
@RequestMapping("rule")
@RestController
public class RuleController {

    @Autowired
    private RuleLoader ruleLoader;

    @Autowired
    private RuleRunner runner;

    @GetMapping("/")
    public String index() {
        System.out.println("index");
        return "success";
    }

    /**
     * 重新加载所有规则
     */
    @GetMapping("reload")
    public String reload() {
        System.out.println("reload all");
        ruleLoader.reload();
        return "success";
    }

    /**
     * 重新加载给定场景下的规则
     *
     * @param scene
     *            场景ID
     */
    @GetMapping("reload/{scene}")
    public String reload(@PathVariable("scene") String scene) {
        System.out.println("reload scene:" + scene);
        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        ruleLoader.reloadScene(scene);
        return "success";
    }

    /**
     * 触发给定场景规则
     *
     * @param scene
     *            场景ID
     */
    @GetMapping("fire/{scene}")
    public String fire(@PathVariable("scene") String scene) {
        System.out.println("fire scene:" + scene);
        runner.execute(scene, null);
        return "success";
    }

}
