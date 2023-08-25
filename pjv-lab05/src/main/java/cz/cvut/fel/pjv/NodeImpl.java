package cz.cvut.fel.pjv;

public class NodeImpl implements Node {
    private NodeImpl leftNode;
    private NodeImpl rightNode;
    private int value;

    @Override
    public NodeImpl getLeft() {
        return leftNode;
    }

    @Override
    public NodeImpl getRight() {
        return rightNode;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setLeft(NodeImpl node) {
        this.leftNode = node;
    }

    public void setRight(NodeImpl node) {
        this.rightNode = node;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
