package com.example.algorithm.binarytree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class IterativeTreeTraversal {

    public static void main(String[] args) {
        int[] values = new int[]{4,2,3,1,6,7,5};
        TreeNode root = new IterativeTreeTraversal().createTree(values);
        new IterativeTreeTraversal().inorder(root);
        System.out.println();
        new IterativeTreeTraversal().preOrder(root);
        System.out.println();
        new IterativeTreeTraversal().postOrder(root);
    }

    private void inorder(TreeNode root){
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.add(curr);
                curr = curr.getLeft();
            }else{
                TreeNode node = stack.pop();
                System.out.print(node.getValue()+" ");
                curr = node.getRight();
            }
        }
    }

    private void preOrder(TreeNode root){
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(curr);
        while(!stack.isEmpty()){
            curr = stack.pop();
            if(curr!=null){
                System.out.print(curr.getValue()+" ");
                if(curr.getRight()!=null){
                    stack.push(curr.getRight());
                }
                if(curr.getLeft()!=null){
                    stack.push(curr.getLeft());
                }
            }
        }
    }

    private void postOrder(TreeNode root){
        TreeNode curr = root;
        Map<Integer, Boolean> visitedMap = new HashMap<>();
        visitedMap.put(null, Boolean.TRUE);
        Stack<TreeNode> stask = new Stack<>();
        stask.push(curr);
        while(!stask.isEmpty()){
            TreeNode node = stask.peek();
            if(isVisited(visitedMap, node.getLeft()) && isVisited(visitedMap, node.getRight())){
                node = stask.pop();
                System.out.print(node.getValue()+" ");
                visitedMap.put(node.getValue(), Boolean.TRUE);
            }else{
                if(node.getRight() != null){
                    stask.push(node.getRight());
                }
                if(node.getLeft() != null){
                    stask.push(node.getLeft());
                }
            }
        }
    }

    private boolean isVisited(Map<Integer, Boolean> visitedMap, TreeNode node){
        return node==null || visitedMap.containsKey(node.getValue());
    }

    private TreeNode createTree(int[] values){
        TreeNode root = null;
        for(int val : values){
            if(root == null){
                root = new TreeNode(val);
            }else{
                add(root, val);
            }
        }
        return root;
    }

    private void add(TreeNode root, int val){
        TreeNode curr = root;
        while(true){
            if(curr.getValue() >= val){
                if(curr.getLeft() == null){
                    curr.setLeft(new TreeNode(val));
                    break;
                }else{
                    curr = curr.getLeft();
                }
            }else{
                if(curr.getRight() == null){
                    curr.setRight(new TreeNode(val));
                    break;
                }else{
                    curr = curr.getRight();
                }
            }
        }
    }
}
