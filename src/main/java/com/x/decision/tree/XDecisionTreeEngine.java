package com.x.decision.tree;

import com.googlecode.aviator.AviatorEvaluator;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 决策引擎
 *
 * @author: www.byteblogs.com
 * @date : 2022-05-20 15:33
 */
public class XDecisionTreeEngine {

    private XDecisionTreeEngine() {}

    /**
     * 执行决策逻辑
     *
     * @param root  节点
     * @param param 执行参数
     * @param log   执行日志跟踪
     * @return 返回决策的节点
     */
    public static TreeNode doDecision(TreeNode root, Map<String, Object> param, StringBuilder log) {

        log.append(root.getName()).append("--->");

        List<TreeNode> next = root.getNext();
        for (TreeNode treeNode : next) {

            if (treeNode.isLeaf()) {
                log.append(treeNode.getName());
                return treeNode;
            }

            String handler = (String) treeNode.getHandler();
            boolean result = (boolean) AviatorEvaluator.execute(handler, param, true);
            if (result) {
                return doDecision(treeNode, param, log);
            }
        }

        throw new UnsupportedOperationException(MessageFormat.format("未匹配到满足条件的case log:[{0}]", log));
    }

}
