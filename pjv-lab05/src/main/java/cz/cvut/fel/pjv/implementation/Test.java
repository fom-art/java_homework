package cz.cvut.fel.pjv.implementation;

import cz.cvut.fel.pjv.TreeImpl;

public class Test {
    public static void main(String[] args) {
        TreeImpl tree = new TreeImpl();
        int[] array = {1, 2, 3, 4, 5};
        tree.setTree(array);
        System.out.println(tree);
    }
}
