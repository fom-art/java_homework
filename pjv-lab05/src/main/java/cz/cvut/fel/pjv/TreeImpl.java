package cz.cvut.fel.pjv;

import java.util.HashMap;

public class TreeImpl implements Tree {
    private NodeImpl root;

    @Override
    public void setTree(int[] values) {
        setRoot(values);
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public String toString() {
        if (this.root != null) {
            return setNodeString(this.root, 0, "");
        } else {
            return "";
        }
    }

    private String setNodeString(NodeImpl node, int depth, String currentString) {
        String resultString = currentString;
        int currentDepth = depth++;
        for (int i = 1; i < depth; i++) {
            resultString += " ";
        }
        resultString += "- " + node.getValue() + "\n";
        if (node.getLeft() != null) {
            resultString = setNodeString(node.getLeft(), depth, resultString);
        }
        if (node.getRight() != null) {
            resultString = setNodeString(node.getRight(), depth, resultString);
        }
        return resultString;
    }

    private void setRoot(int[] values) {
        if (values.length != 0) {
            this.root = new NodeImpl();
            setNode(this.root, values[getMiddleCellIndex(values)], values);
        } else {
            this.root = null;
        }
    }

    private void setNode(NodeImpl node, int value, int[] array) {
        node.setValue(value);
        HashMap<Integer, int[]> seperatedArray = getSeperatedArray(array);

        int[] leftArray = getLeftArrayFromSeperated(seperatedArray);
        int[] rightArray = getRightArrayFromSeperated(seperatedArray);

        //Set left branch
        if (leftArray.length != 0) {
            setLeft(leftArray, node);
        }
        //Set right branch
        if (rightArray.length != 0) {
            setRight(rightArray, node);
        }
    }

    private void setLeft(int[] array, NodeImpl node) {
        //Initialize branch Nodes
        node.setLeft(new NodeImpl());
        setBranch(array, node.getLeft());
    }

    private void setRight(int[] array, NodeImpl node) {
        //Initialize branch Nodes
        node.setRight(new NodeImpl());
        setBranch(array, node.getRight());
    }

    private void setBranch(int[] array, NodeImpl node) {
        if (array.length != 0) {
            setNode(node, array[getMiddleCellIndex(array)], array);
        }
    }

    private int[] getLeftArrayFromSeperated(HashMap<Integer, int[]> seperatedArray) {
        return seperatedArray.get(0);
    }

    private int[] getRightArrayFromSeperated(HashMap<Integer, int[]> seperatedArray) {
        return seperatedArray.get(1);
    }

    private int getMiddleCellIndex(int[] values) {
        return values.length / 2;
    }

    private HashMap<Integer, int[]> getSeperatedArray(int[] array) {
        int middleIndex = getMiddleCellIndex(array);
        HashMap<Integer, int[]> result = new HashMap<Integer, int[]>();
        int[] arrayToAddToMap = new int[middleIndex];
        for (int i = 0; i < arrayToAddToMap.length; i++) {
            arrayToAddToMap[i] = array[i];
        }
        result.put(0, arrayToAddToMap);

        int difference = middleIndex + 1;
        arrayToAddToMap = new int[array.length - difference];
        for (int i = 0; i < arrayToAddToMap.length; i++) {
            arrayToAddToMap[i] = array[i + difference];
        }
        result.put(1, arrayToAddToMap);

        return result;
    }
}
