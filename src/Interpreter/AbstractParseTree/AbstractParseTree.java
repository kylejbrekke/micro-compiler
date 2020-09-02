package Interpreter.AbstractParseTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractParseTree {

    private static final List<String> MATH = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    protected Node root = null;
    protected ArrayList<Node> allNodes = new ArrayList<>();

    public AbstractParseTree() {}

    public Node getRoot() {
        return root;
    }

    public void adjust() {
        for (Node child: root.children) {
            adjust(child);
        }
    }

    public void adjust(Node node) {
        ArrayList<Integer> indicesToMove = new ArrayList<>();
        for (int i = 0; i < node.children.size(); i++) {
            if (MATH.contains(node.children.get(i).getValue())) {
                indicesToMove.add(i + 1);
            }
        }

        for (int i = 0; i < indicesToMove.size(); i++) {
            Node child = node.removeChild(node.children.get(indicesToMove.get(i) - i));
            Node toMoveTo = node.children.get(indicesToMove.get(i) - (i + 1));
            child.setParent(toMoveTo);
        }

        for (Node child: node.children) {
            adjust(child);
        }
    }

    public void printTree() {
        System.out.println(root.value);
        for (Node child: root.children) {
            printTree(child, 1);
        }
    }

    public void printTree(Node node, int indentCount) {
        String indent = "";
        for (int i = 0; i < indentCount; i++) {
            indent = indent.concat("__");
        }
        System.out.println(indent + node.value);

        for (Node child : node.children) {
            printTree(child, indentCount + 1);
        }
    }

}
