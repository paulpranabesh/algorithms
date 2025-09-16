package com.example.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Graph graph = new Graph();
        for(int[] map : prerequisites){
            Node from = graph.getNode(map[1]);
            Node to = graph.getNode(map[0]);
            from.addNeighbour(to);
        }

        for(Node node : graph.nodes){
            if(node.visitStatus == NodeVisitStatus.UNVISITED && cycleDetect(node)){
                return false;
            }
        }
        return true;
    }

    private boolean cycleDetect(Node currNode){
        if(currNode.visitStatus == NodeVisitStatus.VISITING){
            return true;

        }

        currNode.setVisitStatus(NodeVisitStatus.VISITING);
        for(Node neighbour : currNode.neighbours){
            if(cycleDetect(neighbour)){
                return true;
            }
        }
        currNode.setVisitStatus(NodeVisitStatus.VISITED);
        return false;
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
