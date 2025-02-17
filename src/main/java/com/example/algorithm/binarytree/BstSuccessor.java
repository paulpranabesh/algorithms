package com.example.algorithm.binarytree;

public class BstSuccessor {

    public static void main(String[] args) {
        int[] values = new int[]{4,2,3,1,6,7,5};
        TreeNode root = new TreeManager().createTree(values);
        new IterativeTreeTraversal().inorder(root);
        System.out.println();
        TreeNode  successor = new BstSuccessor().findSuccessor(3, root);
        System.out.println(successor != null ? successor.getValue(): "NA");
    }

    public TreeNode findSuccessor(int val, TreeNode root){
        TreeNode successor = null;
        TreeNode curr = root;

        while(curr!=null){
            if(curr.getValue() > val){
                successor = curr;
                curr = curr.getLeft();
            }else if(curr.getValue() < val){
                curr = curr.getRight();
            }else {
                if(curr.getRight()!=null){
                    return curr.getRight();
                }
                return successor;
            }
        }
        return successor;

    }
}
