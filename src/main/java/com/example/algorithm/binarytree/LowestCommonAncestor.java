package com.example.algorithm.binarytree;

import java.util.HashMap;
import java.util.Map;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        int[] values = new int[]{4, 2, 3, 1, 6, 7, 5};
        TreeNode root = new TreeManager().createTree(values);
        Integer lca = new LowestCommonAncestor().findLCA(root, 2, 1);
        System.out.println(lca);
    }

    private Integer findLCA(TreeNode root, int a, int b){
        if(root == null){
            return null;
        }
        if(root.getValue() == a || root.getValue() == b){
            return root.getValue();
        }
        Integer lLca = findLCA(root.getLeft(),  a,  b);
        Integer rLca = findLCA(root.getRight(),  a,  b);
        if(lLca != null && rLca != null){
            return root.getValue();
        }
        return lLca != null ? lLca : (rLca != null ? rLca : null);
    }


    private static class Result {

        private Integer lca = null;
        private Map<Integer, Boolean> visitLog = new HashMap<>();

        public Result(Result r){
            visitLog.putAll(r.visitLog);
        }

        public Result(){
        }
        public boolean found(Integer num) {
            return visitLog.containsKey(num);
        }

        public void add(Integer num) {
            visitLog.put(num, Boolean.TRUE);
        }

        public boolean isFoundBoth(Integer a, Integer b){
            return visitLog.containsKey(a) && visitLog.containsKey(b);
        }
    }
}
