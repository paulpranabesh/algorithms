package com.example.algorithm.binarytree;

/**
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 * https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class LongestZigZagPath {

    public int longestZigZag(TreeNode root) {
        if(root == null){
            return 0;
        }
        Result rLeft = findLongest(root.left, Direction.LEFT, new Result().increment());
        Result rRight = findLongest(root.right, Direction.RIGHT, new Result().increment());
        return rLeft.length > rRight.length ? rLeft.length : rRight.length;
    }

    Result findLongest(TreeNode root, Direction direction, Result result ){
        if(root == null){
            return new Result(result).decrement();
        }
        Result rLeft = new Result();
        Result rRight = new Result();
        switch(direction){
            case LEFT:
                rRight = findLongest(root.right, Direction.RIGHT, new Result(result).increment());
                Result lFresh = findLongest(root.left, Direction.LEFT, new Result().increment());
                rRight = rRight.length > lFresh.length ? rRight : lFresh;
                break;
            case RIGHT:
                rLeft = findLongest(root.left, Direction.LEFT, new Result(result).increment());
                Result rFresh = findLongest(root.right, Direction.RIGHT, new Result().increment());
                rRight = rLeft.length > rFresh.length ? rLeft : rFresh;

                break;
        }
        return rLeft.length > rRight.length ? rLeft : rRight;
    }

    public class TreeNode {
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
    private class Result{
        int length = 0;
        public Result(Result r){
            this.length = r.length;
        }
        public Result increment(){
            this.length += 1;
            return this;
        }

        public Result decrement(){
            this.length -= 1;
            return this;
        }

        public Result(){
        }
    }

    private enum Direction{
        LEFT, RIGHT;
    }
}
