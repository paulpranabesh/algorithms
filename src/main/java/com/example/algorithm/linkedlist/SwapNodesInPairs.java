package com.example.algorithm.linkedlist;

/**
 * Given a linked list,
 * swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values
 * in the list's nodes (i.e., only nodes themselves may be changed.)
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode resultHead = null;
        ListNode w1 = null;
        ListNode w2 = null;
        ListNode nextWorkingNode = null;
        ListNode end = null;
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            ListNode newHead = head.next;
            newHead.next = head;
            head.next = null;
            return newHead;
        }
        w1 = head;
        w2 = head.next;

        while (true) {
            nextWorkingNode = w2.next;
            ListNode nextHead = swap(w1, w2);
            if (resultHead == null) {
                resultHead = nextHead;
            } else {
                end.next = nextHead;
            }
            end = nextHead.next;
            if (nextWorkingNode == null) {
                break;
            }
            if (nextWorkingNode.next == null) {
                end.next = nextWorkingNode;
                break;
            }
            w1 = nextWorkingNode;
            w2 = nextWorkingNode.next;
        }
        return resultHead;
    }

    private ListNode swap(ListNode w1, ListNode w2) {
        w2.next = w1;
        w1.next = null;
        return w2;
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
