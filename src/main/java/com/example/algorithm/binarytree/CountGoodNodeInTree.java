package com.example.algorithm.binarytree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 */
public class CountGoodNodeInTree {


    public int goodNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        AtomicInteger count = new AtomicInteger(0);
        findNodes(root, Integer.MIN_VALUE, count);
        return count.get();

    }

    private  void findNodes(TreeNode root, int max, AtomicInteger count) {
        if(root == null){
            return;
        }
        if(root.val>=max){
            count.incrementAndGet();
        }
        max = Math.max(max, root.val);
        findNodes(root.left,  max,  count);
        findNodes(root.right,  max,  count);
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
