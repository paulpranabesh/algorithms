package com.example.algorithm.binarytree;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSalestNumberInBST {

    public int kthSmallest(TreeNode root, int k) {
        Queue<Integer> q = new PriorityQueue<>(Comparator.comparing(Integer::intValue, Comparator.reverseOrder()));
        traverse(root, q,  k);
        if(q.size() <k){
            return -1;
        }

        return q.peek();


    }

    public void traverse(TreeNode root, Queue<Integer> q, int k) {
        if(root == null){
            return;
        }
        if(q.size()<k){
            q.add(root.val);
        }else {
            if(q.peek()>root.val){
                q.poll();
                q.offer(root.val);
            }
        }
        traverse(root.left, q, k);
        traverse(root.right, q, k);
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
