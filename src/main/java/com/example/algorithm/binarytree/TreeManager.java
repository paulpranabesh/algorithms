package com.example.algorithm.binarytree;

public class TreeManager {

    public TreeNode createTree(int[] values){
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

    public void add(TreeNode root, int val){
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
