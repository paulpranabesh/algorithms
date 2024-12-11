package com.example.algorithm.linkedlist;

import java.util.Arrays;

public class SortLinkedList {

    public static void main(String[] args) {
        Node head = null;
        Node currNode = null;
        for(Integer data  : Arrays.asList(7,4,2,56,8,34,5,78,1)){
            if(head == null){
                head = currNode = new Node(data);
            }else{
                currNode.setNext(new Node(data));
                currNode = currNode.getNext();
            }
        }
        Node sortedHead = sortLinkedList(head);
        while(sortedHead != null){
            System.out.println(sortedHead.getData());
            sortedHead = sortedHead.getNext();
        }
    }

    private static Node sortLinkedList(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node first = head;
        Node currEnd = head;
        Node second = head;
        while (second != null && first != null) {
            if (second.getNext() == null) {
                break;
            }
            currEnd = first;
            first = first.getNext();
            second = second.getNext().getNext();
        }
        Node n1 = head;
        Node n2 = currEnd.getNext();
        currEnd.setNext(null);

        Node node1 = sortLinkedList(n1);
        Node node2 = sortLinkedList(n2);
        Node newHead = null;
        Node currNode = null;
        while (node1 != null && node2 != null) {
            if (node1.getData() < node2.getData()) {
                if (newHead == null) {
                    newHead = node1;
                    currNode = node1;
                } else {
                    currNode.setNext(node1);
                    currNode = currNode.getNext();
                }
                node1 = node1.getNext();

            } else {
                if (newHead == null) {
                    newHead = node2;
                    currNode = node2;
                } else {
                    currNode.setNext(node2);
                    currNode = currNode.getNext();
                }
                node2 = node2.getNext();
            }
        }

        while (node1 != null) {
            if (newHead == null) {
                newHead = node1;
                currNode = node1;
            } else {
                currNode.setNext(node1);
                currNode = currNode.getNext();
            }
            node1 = node1.getNext();
        }

        while (node2 != null) {
            if (newHead == null) {
                newHead = node2;
                currNode = node2;
            } else {
                currNode.setNext(node2);
                currNode = currNode.getNext();
            }
            node2 = node2.getNext();
        }

        return newHead;
    }

    private Node minValuedNode(Node n1, Node n2){
        if(n1 == null){
            return n2;
        }else if(n2 == null){
            return n1;
        }else if(n1.getData() > n2.getData()){
            return n2;
        }
        else{
            return n1;
        }
    }


    private static class Node{
        private int data;
        private Node next = null;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
