package com.example.algorithm.binarytree;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        if(left!=null){
            invertTree(left);
        }
        if(right!=null){
            invertTree(right);
        }
        return root;

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
