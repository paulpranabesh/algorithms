package com.example.algorithm.binarytree;

public class BSTRangeValues {
    public static void main(String[] args) {
        int[] values = new int[]{8,3,10,1,6,4,7,14,13};
        TreeNode root = new TreeManager().createTree(values);
        new BSTRangeValues().printValuesInRange(5,8,  root);
    }

    private void printValuesInRange(int a, int b, TreeNode root){
        TreeNode  successor = new BstSuccessor().findSuccessor(a, root);
        while(successor != null && successor.getValue()<= b){
            System.out.println(successor.getValue());
             successor = new BstSuccessor().findSuccessor(successor.getValue(), root);
        }
    }

}
