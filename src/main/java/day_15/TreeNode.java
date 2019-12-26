package day_15;

import day_10.Position;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Position position;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent;

    public TreeNode(final Position position) {
        this.position = position;
    }

    public void addChild(final TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public Position getPosition() {
        return position;
    }

    private void setParent(final TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }
}
