package com.example.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

public class RightSideViewOfTree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;

        if (depth == result.size()) {
            result.add(node.val); // first time we reach this depth
        }

        // Prioritize right child
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }
    }
}
