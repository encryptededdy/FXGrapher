package nz.zhang.graph;

import java.util.*;

public class Node {
    private String name;
    private Integer cost = 0;
    private Map<Node, Integer> children = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public void addChild(Node node, int cost) {
        children.put(node, cost);
    }

    public Map<Node, Integer> getChildren() {
        return children;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name;
    }
}
