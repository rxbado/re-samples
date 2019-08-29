package com.rsoft.drools;

import com.rsoft.DroolsSample;
import com.rsoft.ruleengine.RuleExecutor;
import com.rsoft.samples.model.Order;
import com.rsoft.samples.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsSample.class)
public class DroolsSceneTests {
    @Autowired
    RuleExecutor ruleExecutor;

    @Test
    public void fireRulesTest() throws Exception {
        List<Order> orderList = getInitData();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            ruleExecutor.execute("signin", order);
            this.addScore(order);
        }

    }

    private static void addScore(Order o) {
        System.out.println("用户" + o.getUser().getUsername() + "增加积分: " + o.getScore());
    }

    private static List<Order> getInitData() throws Exception {
        List<Order> orderList = new ArrayList<Order>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        {
            Order order = new Order();
            order.setAmout(80);
            order.setBookingDate(df.parse("2019-07-01"));
            User user = new User();
            user.setLevel(1);
            user.setUsername("张三");
            order.setUser(user);
            order.setScore(111);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(200);
            order.setBookingDate(df.parse("2019-07-02"));
            User user = new User();
            user.setLevel(2);
            user.setUsername("李四");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(800);
            order.setBookingDate(df.parse("2019-07-03"));
            User user = new User();
            user.setLevel(3);
            user.setUsername("王二");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(1500);
            order.setBookingDate(df.parse("2019-07-04"));
            User user = new User();
            user.setLevel(4);
            user.setUsername("小米");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(2500);
            order.setBookingDate(df.parse("2019-07-23"));
            User user = new User();
            user.setLevel(5);
            user.setUsername("小明");
            order.setUser(user);
            orderList.add(order);
        }
        return orderList;
    }
}
