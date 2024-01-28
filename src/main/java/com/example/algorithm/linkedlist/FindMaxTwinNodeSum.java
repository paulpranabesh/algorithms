package com.example.algorithm.linkedlist;

public class FindMaxTwinNodeSum {

    public int pairSum(ListNode head) {
        Result r = findMax(head, head);
        return r.max;
    }

    private Result findMax(ListNode node1, ListNode node2){
        if(node2 == null){
            return new Result(node1, 0);
        }
        Result result = findMax(node1, node2.next);
        if(result.done){
            return result;
        }
        ListNode currN = result.currNode;
        Result newResult = new Result(currN.next, result.max);
        if(currN.val + node2.val > result.max ){
            newResult.max = currN.val + node2.val;
        }
        newResult.done = currN.next == node2;
        return newResult;
    }
    class Result{
        ListNode currNode;
        int max;

        boolean done;

        public Result(ListNode currNode, int max) {
            this.currNode = currNode;
            this.max = max;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
