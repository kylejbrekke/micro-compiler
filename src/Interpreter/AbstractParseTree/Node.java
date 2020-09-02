package Interpreter.AbstractParseTree;

import java.util.ArrayList;

public class Node {
    protected String value = "";
    protected Node parent = null;
    protected ArrayList<Node> children = new ArrayList<>();

    public Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.parent.children.add(this);
    }

    public Node(String value) {
        this.value = value;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setParent(Node node) {
        parent = node;
        parent.children.add(this);
    }

    public Node removeChild(Node node) {
        children.remove(node);
        return node;
    }

    public Node getParent() {
        return parent;
    }

    public String getValue() {
        return value;
    }
}
