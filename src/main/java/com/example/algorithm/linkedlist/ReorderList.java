package com.example.algorithm.linkedlist;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class ReorderList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        @Override
        public String toString() {
            return val + "";
        }
    }

    public static void main(String[] args) {
        ReorderList instance =  new ReorderList();
        ListNode head = instance.reorderList(instance.createList());
        ListNode curr = head;
        while(curr!=null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
    }

    private ListNode createList(){
        ListNode head = null, curr = null;
        for(int i=1; i<=4; i++){
            if(head == null){
                head = curr = new ListNode(i);
            }else{
                curr.next = new ListNode(i);
                curr = curr.next;
            }
        }
        return head;
    }

    public ListNode reorderList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        doReorder(head, head.next);
        return head;
    }

    private LinkConnectionData doReorder(ListNode x, ListNode y) {
        if (y == null) {
            return null;
        }
        LinkConnectionData connData = doReorder(x, y.next);
        if (connData == null) {
            ListNode nextActiveX = x.next;
            LinkConnectionData returnData = new LinkConnectionData(y, nextActiveX);
            x.next = y;
            return returnData;
        } else {
            if (!connData.stop) {
                ListNode activeX = connData.activeX;
                ListNode prev = connData.prev;
                if (y.next == activeX) {
                    LinkConnectionData data = new LinkConnectionData();
                    data.stop = true;
                    return data;
                }
                ListNode nextActiveX = activeX.next;
                LinkConnectionData returnData = new LinkConnectionData(y, nextActiveX);
                activeX.next = y;
                y.next = null;
                prev.next = activeX;
                return returnData;
            } else {
                return connData;
            }
        }
    }

    private class LinkConnectionData{
        ListNode prev;
        ListNode activeX;

        boolean stop = false;

        public LinkConnectionData() {
        }

        public LinkConnectionData(ListNode prev, ListNode activeX) {
            this.prev = prev;
            this.activeX = activeX;
        }
    }
}
