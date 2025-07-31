package com.example.algorithm.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the path that has the max weightage
 */
public class SerializeAndDeserializeBinaryTree {


    // Serialize the tree using BFS
    public String serialize(TreeNode root) {
        if(root == null){
            return "null";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuffer sb = new StringBuffer();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null ){
                sb.append("null");
            }else{
                sb.append(node.val+"");
                queue.offer(node.left);
                queue.offer(node.right);
            }
            sb.append(",");
        }
        return sb.toString();
    }

    // Deserialize the string back into a binary tree using BFS
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;

        String[] values = data.split(",");
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(values[i++]));
        TreeNode head = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && i < values.length) {
            TreeNode currentNode = queue.poll();

            if(i < values.length && !values[i].equals("null")){
                currentNode.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(currentNode.left);

            }
            i++;
            if(i < values.length && !values[i].equals("null")){
                currentNode.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(currentNode.right);

            }
            i++;
        }

        return head;
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
}
