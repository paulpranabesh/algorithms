package com.example.algorithm.binarytree;

/**
 * Find the path that has the max weightage
 */
public class BinaryTreeMaximumPathSum {


    private int maxVal = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        doIt(root);
        return maxVal;
    }

    public int doIt(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = Math.max(0,doIt(root.left));
        int right = Math.max(0,doIt(root.right));
        maxVal = Math.max(maxVal, left+root.val+right);
        return Math.max(left+root.val, root.val+right);
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
