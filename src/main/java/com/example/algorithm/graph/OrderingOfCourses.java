package com.example.algorithm.graph;

import java.util.*;
import java.util.stream.IntStream;

public class OrderingOfCourses {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Graph graph = new Graph();
        for(int[] map : prerequisites){
            Node from = graph.getNode(map[1]);
            Node to = graph.getNode(map[0]);
            from.addNeighbour(to);
        }
        IntStream.range(0, numCourses).forEach(i->graph.getNode(i));
        Stack<Node> nodeOrder = new Stack<>();
        for(Node node : graph.nodes){
            if(node.visitStatus == NodeVisitStatus.UNVISITED){
                boolean success= dfs(node, nodeOrder);
                if(!success){
                    return new int[]{};
                }
            }

        }
        int[] result = new int[numCourses];
        int index= 0;
        while(!nodeOrder.isEmpty()){
            result[index++] = nodeOrder.pop().id;
        }
        return result;

    }

    private boolean dfs(Node currNode, Stack<Node> visited){
        if(currNode.visitStatus == NodeVisitStatus.VISITING){
            return false;
        }
        if(currNode.visitStatus == NodeVisitStatus.VISITED){
            return true;
        }
        currNode.setVisitStatus(NodeVisitStatus.VISITING);
        for(Node neighbour : currNode.neighbours){
            if(!dfs(neighbour, visited)){
                return false;
            }
        }
        currNode.setVisitStatus(NodeVisitStatus.VISITED);
        visited.push(currNode);
        return true;
    }

    private class Graph{
        private List<Node> nodes;

        public Graph(){
            this.nodes = new ArrayList<>();
        }

        public Node getNode(int id) {
            Optional<Node> selectedNode =  nodes.stream().filter(node->node.id == id).findFirst();
            if(selectedNode.isPresent()){
                return selectedNode.get();
            }
            Node newNode = new Node(id);
            nodes.add(newNode);
            return newNode;
        }

        public void addNode(Node node){
            if(nodes.contains(node)){
                return;
            }
            nodes.add(node);
        }

    }

    private class Node{
        private int id;
        private List<Node> neighbours;
        private NodeVisitStatus visitStatus = NodeVisitStatus.UNVISITED;
        public Node(int id) {
            this.id = id;
            this.neighbours = new ArrayList<>();
        }
        public void addNeighbour(Node node){
            if(!neighbours.contains(node)){
                neighbours.add(node);
            }
        }

        public void setVisitStatus(NodeVisitStatus visitStatus) {
            this.visitStatus = visitStatus;
        }


        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    public enum NodeVisitStatus {
        UNVISITED, VISITING, VISITED;
    }
}
