package com.example.algorithm.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;

    public boolean contains(Integer data){
       return nodes.stream().anyMatch(node->node.value().equals(data));
    }

    public Optional<Node> find(Integer data){
        return nodes.stream().filter(node->node.value().equals(data)).findFirst();
    }
    public Graph() {
        this.nodes = new HashSet<>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public Set<Node> allNodes(){
       return new HashSet<>(nodes);
    }

}
