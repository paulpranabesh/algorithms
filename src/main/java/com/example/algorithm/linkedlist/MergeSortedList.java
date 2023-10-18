package com.example.algorithm.linkedlist;

public class MergeSortedList {

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;;
        ListNode newCurr = null;
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        if(list1 == null && list2 == null){
            return null;
        }

        if(list1 == null ){
            return list2;
        }

        if(list2 == null ){
            return list1;
        }

        for(; curr1 != null && curr2 != null; ){
            ListNode workingNode = null;
            if(curr1.val < curr2.val){
                workingNode = curr1;
                curr1 = curr1.next;
            }else{
                workingNode = curr2;
                curr2 = curr2.next;
            }
            if(newCurr == null){
                newCurr = head = workingNode;
            }else{
                newCurr.next = workingNode;
                newCurr = workingNode;
            }
        }
        while(curr1 != null){
            newCurr.next = curr1;
            newCurr = newCurr.next;
            curr1 = curr1.next;
        }

        while(curr2 != null){
            newCurr.next = curr2;
            newCurr = newCurr.next;
            curr2 = curr2.next;
        }
        return head;
    }
}
