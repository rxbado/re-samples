package com.rsoft.drools;

import org.kie.api.runtime.rule.RuleRuntime;
import org.kie.api.runtime.rule.StatelessRuleSession;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;

import javax.rules.ConfigurationException;
import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;

/**
 * http://www.voidcn.com/article/p-zevvdfet-vp.html
 * 
 * @author rsoft
 *
 */
public class JSR94Sample {
    private RuleServiceProvider ruleProvider;

    public static void main(String... strings) {
        new JSR94Sample().run();
    }

    public void run() {
        String uri = RuleServiceProviderImpl.RULE_SERVICE_PROVIDER;
        try {
            // 1.注册ruleProvider,并且从RuleServiceProviderManager获取ruleProvider
            RuleServiceProviderManager.registerRuleServiceProvider(uri, RuleServiceProviderImpl.class);
            ruleProvider = RuleServiceProviderManager.getRuleServiceProvider(uri);

            HashMap<String, String> properties = new HashMap<String, String>();

            // 2.获取RuleAdministrator实例,获取RuleExectuionSetProvider
            RuleAdministrator admin = ruleProvider.getRuleAdministrator();
            LocalRuleExecutionSetProvider ruleExecutionSetProvider = admin.getLocalRuleExecutionSetProvider(properties);

            // 3.创建RuleExecutionSet
            Reader reader = new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("info/syang/stu1/addpoint.drl"));
            RuleExecutionSet reSet = ruleExecutionSetProvider.createRuleExecutionSet(reader, properties);

            // 4.注册RuleExecutionSet
            admin.registerRuleExecutionSet("mysample", reSet, properties);

            // 5.获取RuleRuntime, 创建会话
            RuleRuntime runtime = ruleProvider.getRuleRuntime();
            StatelessRuleSession ruleSession = (StatelessRuleSession) runtime.createRuleSession("mysample", null,
                    RuleRuntime.STATELESS_SESSION_TYPE);

            // 6.运行实例
            PointDomain pointDomain = new PointDomain();
            fillPointDomain(pointDomain);

            ruleSession.executeRules(Arrays.asList(pointDomain));
            // @SuppressWarnings("unchecked")
            // List<PointDomain> results = (List<PointDomain>)ruleSession.executeRules(Arrays.asList(pointDomain));

            System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
            System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
            System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());

            System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());

        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RuleExecutionSetCreateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuleExecutionSetRegisterException e) {
            e.printStackTrace();
        } catch (RuleSessionTypeUnsupportedException e) {
            e.printStackTrace();
        } catch (RuleSessionCreateException e) {
            e.printStackTrace();
        } catch (RuleExecutionSetNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidRuleSessionException e) {
            e.printStackTrace();
        }
    }

    private void fillPointDomain(PointDomain pointDomain) {
        pointDomain.setUserName("hello kity");
        pointDomain.setBackMondy(100d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(true);
        pointDomain.setPoint(0l);
    }

    private class PointDomain {

        private String userName;
        // 是否当日生日
        private boolean birthDay;
        // 增加积分数目
        private long point;
        // 当月购物次数
        private int buyNums;
        // 当月退货次数
        private int backNums;
        // 当月购物总金额
        private double buyMoney;
        // 当月退货总金额
        private double backMondy;
        // 当月信用卡还款次数
        private int billThisMonth;

        /**
         * 记录积分发送流水，防止重复发放
         * 
         * @param userName
         * @param type
         */
        public void recordPointLog(String userName, String type) {
            System.out.println("增加对" + userName + "的类型为" + type + "的积分操作记录.");
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isBirthDay() {
            return birthDay;
        }

        public void setBirthDay(boolean birthDay) {
            this.birthDay = birthDay;
        }

        public long getPoint() {
            return point;
        }

        public void setPoint(long point) {
            this.point = point;
        }

        public int getBuyNums() {
            return buyNums;
        }

        public void setBuyNums(int buyNums) {
            this.buyNums = buyNums;
        }

        public int getBackNums() {
            return backNums;
        }

        public void setBackNums(int backNums) {
            this.backNums = backNums;
        }

        public double getBuyMoney() {
            return buyMoney;
        }

        public void setBuyMoney(double buyMoney) {
            this.buyMoney = buyMoney;
        }

        public double getBackMondy() {
            return backMondy;
        }

        public void setBackMondy(double backMondy) {
            this.backMondy = backMondy;
        }

        public int getBillThisMonth() {
            return billThisMonth;
        }

        public void setBillThisMonth(int billThisMonth) {
            this.billThisMonth = billThisMonth;
        }
    }
}
