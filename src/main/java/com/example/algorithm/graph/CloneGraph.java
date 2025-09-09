package com.example.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    private Map<Integer, Node> nodeStore = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return cloneHelper(node);
    }

    private Node cloneHelper(Node root) {
        if(root == null){
            return null;
        }

        if(nodeStore.containsKey(root.val)){
            return nodeStore.get(root.val);
        }

        Node newNode = new Node(root.val);
        nodeStore.put(root.val, newNode);
        for (Node neighbor : root.neighbors) {
            newNode.neighbors.add(cloneHelper(neighbor));
        }
        return newNode;
    }


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
