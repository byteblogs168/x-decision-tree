package com.x.decision.tree;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

@Slf4j
public class XDecisionTreeApplicationTests {

    @Test
    public void contextLoads() {

        try {
            Context context = new Context();
            context.setWeather("阴");

            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), UUID.randomUUID().toString());
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {
            log.error("测试失败", e);
        }

        try {
            Context context = new Context();
            context.setWeather("晴");
            context.setHumidity("high");

            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), UUID.randomUUID().toString());
            Assert.assertEquals("执行活动活动取消处理器", treeNode.getHandler());
        } catch (Exception e) {
            log.error("测试失败", e);
        }


        try {
            Context context = new Context();
            context.setWeather("晴");
            context.setHumidity("normal");

            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), UUID.randomUUID().toString());
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {
            log.error("测试失败", e);
        }

        try {
            Context context = new Context();
            context.setWeather("雨");
            context.setWindSpeed("strong");
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), UUID.randomUUID().toString());
            Assert.assertEquals("执行活动活动取消处理器", treeNode.getHandler());
        } catch (Exception e) {
            log.error("测试失败", e);
        }

        try {
            Context context = new Context();
            context.setWeather("雨");
            context.setWindSpeed("weak");

            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), UUID.randomUUID().toString());
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {
            log.error("测试失败", e);
        }


    }

    @Data
    public static class Context {
        private String weather;
        private String humidity;
        private String windSpeed;

        public Map<String, Object> buildMap() {

            Map<String, Object> map = new HashMap<>();
            map.put("weather", weather);
            map.put("humidity", humidity);
            map.put("windSpeed", windSpeed);
            return map;
        }

    }

    /**
     * 构建决策树
     *
     * @return 根节点
     */
    private TreeNode buildDecisionTree() {

        return TreeNode.buildTreeNode("根节点", Arrays.asList(
                TreeNode.buildTreeNode("天气阴", Arrays.asList(
                        TreeNode.buildTreeNode("活动进行", "执行活动正常进行处理器")
                ), "weather == '阴'"),
                TreeNode.buildTreeNode("天气晴", Arrays.asList(
                        TreeNode.buildTreeNode("湿度高", Collections.singletonList(TreeNode.buildTreeNode("活动取消", "执行活动活动取消处理器")), "humidity == 'high'"),
                        TreeNode.buildTreeNode("湿度正常", Collections.singletonList(TreeNode.buildTreeNode("活动进行", "执行活动正常进行处理器")), "humidity == 'normal'")
                ), "weather == '晴'"),
                TreeNode.buildTreeNode("天气雨", Arrays.asList(
                        TreeNode.buildTreeNode("风速强", Collections.singletonList(TreeNode.buildTreeNode("活动取消", "执行活动活动取消处理器")), "windSpeed == 'strong'"),
                        TreeNode.buildTreeNode("风速弱", Collections.singletonList(TreeNode.buildTreeNode("活动进行", "执行活动正常进行处理器")), "windSpeed == 'weak'")
                ), "weather == '雨'")
        ), "");
    }



}
