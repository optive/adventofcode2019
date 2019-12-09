package day_06;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Node> children = new ArrayList<>();
    private Node parent;

    public Node(final String name) {
        this.name = name;
    }

    public void addChild(final Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    private void setParent(final Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}