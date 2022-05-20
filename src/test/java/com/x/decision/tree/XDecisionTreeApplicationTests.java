package com.x.decision.tree;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class XDecisionTreeApplicationTests {

    @Test
    public void contextLoads() {

        try {
            Context context = new Context();
            context.setWeather("阴");

            StringBuilder sb = new StringBuilder();
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), sb);
            System.out.println(sb);
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {

        }

        try {
            Context context = new Context();
            context.setWeather("晴");
            context.setHumidity("high");

            StringBuilder sb = new StringBuilder();
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), sb);
            System.out.println(sb);
            Assert.assertEquals("执行活动活动取消处理器", treeNode.getHandler());
        } catch (Exception e) {

        }


        try {
            Context context = new Context();
            context.setWeather("晴");
            context.setHumidity("normal");

            StringBuilder sb = new StringBuilder();
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), sb);
            System.out.println(sb);
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {
        }

        try {
            Context context = new Context();
            context.setWeather("雨");
            context.setWindSpeed("strong");

            StringBuilder sb = new StringBuilder();
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), sb);
            System.out.println(sb);
            Assert.assertEquals("执行活动活动取消处理器", treeNode.getHandler());
        } catch (Exception e) {
        }

        try {
            Context context = new Context();
            context.setWeather("雨");
            context.setWindSpeed("weak");

            StringBuilder sb = new StringBuilder();
            TreeNode treeNode = XDecisionTreeEngine.doDecision(buildDecisionTree(), context.buildMap(), sb);
            System.out.println(sb);
            Assert.assertEquals("执行活动正常进行处理器", treeNode.getHandler());
        } catch (Exception e) {
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

        return createTreeNode("根节点", Arrays.asList(
                createTreeNode("天气阴", Arrays.asList(
                        createTreeNode("活动进行", "执行活动正常进行处理器")
                ), "weather == '阴'"),
                createTreeNode("天气晴", Arrays.asList(
                        createTreeNode("湿度高", Collections.singletonList(createTreeNode("活动取消", "执行活动活动取消处理器")), "humidity == 'high'"),
                        createTreeNode("湿度正常", Collections.singletonList(createTreeNode("活动进行", "执行活动正常进行处理器")), "humidity == 'normal'")
                ), "weather == '晴'"),
                createTreeNode("天气雨", Arrays.asList(
                        createTreeNode("风速强", Collections.singletonList(createTreeNode("活动取消", "执行活动活动取消处理器")), "windSpeed == 'strong'"),
                        createTreeNode("风速弱", Collections.singletonList(createTreeNode("活动进行", "执行活动正常进行处理器")), "windSpeed == 'weak'")
                ), "weather == '雨'")
        ), "");
    }


    /**
     * 创建叶子节点的树节点
     */
    public static TreeNode createTreeNode(final String name, final Object handler) {
        return createTreeNode(name, null, true, handler);
    }

    /**
     * 创建非叶子节点的树节点
     */
    public static TreeNode createTreeNode(final String name, final List<TreeNode> treeNodes, final Object handler) {
        return createTreeNode(name, treeNodes, false, handler);
    }

    /**
     * 创建树节点
     */
    public static TreeNode createTreeNode(final String name, final List<TreeNode> treeNodes,
                                          final boolean isLeaf, final Object handler) {
        TreeNode treeNode = new TreeNode();
        treeNode.setLeaf(isLeaf);
        treeNode.setName(name);
        treeNode.setNext(treeNodes);
        treeNode.setHandler(handler);
        return treeNode;
    }
}
