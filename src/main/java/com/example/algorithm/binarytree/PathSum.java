package com.example.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node
 * values in the path equals targetSum. Each path should be
 * returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and
 * ending at any leaf node. A leaf is a node with no children.
 */

public class PathSum {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, result, new ArrayList<>(), 0, targetSum);
        return result;
    }

    public void traverse(TreeNode root, List<List<Integer>> result, List<Integer> path,
                         int sumSoFar, int targetSum) {
        if(root == null){
            return;
        }
        if(isLeafe(root)){
            if(sumSoFar+root.val == targetSum){
                path.add(root.val);
                result.add(path);
            }
            return;
        }
        if(root.left != null){
            List<Integer> copyPath = new ArrayList<>();
            copyPath.addAll(path);
            copyPath.add(root.val);
            traverse(root.left, result, copyPath, sumSoFar+root.val , targetSum);
        }
        if(root.right != null){
            List<Integer> copyPath = new ArrayList<>();
            copyPath.addAll(path);
            copyPath.add(root.val);
            traverse(root.right, result, copyPath, sumSoFar+root.val, targetSum );
        }
    }

    private boolean isLeafe(TreeNode root){
        return root.left == null && root.right == null;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
