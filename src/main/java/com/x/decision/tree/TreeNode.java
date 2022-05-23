package com.x.decision.tree;

import lombok.Data;

import java.util.List;

/**
 * 决策树节点
 *
 * @author: www.byteblogs.com
 * @date : 2022-05-20 15:32
 */
@Data
public class TreeNode {

    /**
     * 节点名称
     */
    private String name;

    /**
     * 子节点列表
     */
    private List<TreeNode> next;

    /**
     * 是否叶子节点
     */
    private boolean isLeaf;

    /**
     * 叶子节点是aviator表达式, 非叶子节点是具体处理器
     */
    private Object handler;

    /**
     * 创建叶子节点的树节点
     */
    public static TreeNode buildTreeNode(final String name, final Object handler) {
        return buildTreeNode(name, null, true, handler);
    }

    /**
     * 创建非叶子节点的树节点
     */
    public static TreeNode buildTreeNode(final String name, final List<TreeNode> treeNodes, final Object handler) {
        return buildTreeNode(name, treeNodes, false, handler);
    }

    /**
     * 创建树节点
     */
    public static TreeNode buildTreeNode(final String name, final List<TreeNode> treeNodes,
                                          final boolean isLeaf, final Object handler) {
        TreeNode treeNode = new TreeNode();
        treeNode.setLeaf(isLeaf);
        treeNode.setName(name);
        treeNode.setNext(treeNodes);
        treeNode.setHandler(handler);
        return treeNode;
    }
}
