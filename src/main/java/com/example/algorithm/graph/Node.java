package com.example.algorithm.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Node {

    private Integer data;
    private Integer level;
    private Set<Node> neighbours;

    private NodeVisitStatus visitStatus = NodeVisitStatus.UNVISITED;

    public Node(int data) {
        this.data = data;
        this.neighbours = new HashSet<>();
    }

    private boolean isNeighbour(Integer i){
        return neighbours.stream().anyMatch(n->n.data.equals(i));
    }

    public Set<Node> neighbours(){
        return new HashSet<>(neighbours);
    }

    private Optional<Node> getNeighbour(Integer i){
        return neighbours.stream().filter(n->n.data.equals(i)).findFirst();
    }

    public void addNeighbour(Node node){
        if(!isNeighbour(node.data)){
            neighbours.add(node);
        }
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setVisitStatus(NodeVisitStatus visitStatus) {
        this.visitStatus = visitStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public NodeVisitStatus getVisitStatus() {
        return visitStatus;
    }

    public Integer value(){
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return data.equals(node.data);
    }

    @Override
    public String toString() {
        return "Node" + data;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
