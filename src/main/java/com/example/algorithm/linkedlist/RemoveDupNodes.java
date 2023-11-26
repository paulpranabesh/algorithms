package com.example.algorithm.linkedlist;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 */
public class RemoveDupNodes {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode resultHead = null;
        if(head == null || head.next == null){
          return head;
        }
        while( curr!= null){
            boolean changes = false;
            while(curr.next != null && curr.val == curr.next.val){
                curr = curr.next;
                changes = true;
            }
            if(changes && curr != null){
                curr = curr.next;
                continue;
            }
            if(prev == null){
                resultHead = prev = curr;
            }else{
                prev.next = curr;
                prev = curr;
            }
            curr = curr.next;
        }
        if(prev!=null){
            prev.next = null;
        }
        return resultHead;
    }


    private  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
