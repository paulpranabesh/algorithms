package com.example.algorithm.binarytree;

public class BinarySearchTree {

    public static void printInorder(TreeNodes node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.value + " ");
        printInorder(node.right);
    }

    public static void printPreorder(TreeNodes node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public static void printPostorder(TreeNodes node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        TreeNodes TempNode;
        TreeNodes prevNode;
        TreeNodes root = null;
        int array[] = {5, 1, 15, 9, 7, 12, 30, 25, 40, 45, 42};

        for (int i = 0; i < array.length; i++) {
            TreeNodes node = new TreeNodes(array[i]);
            if (root == null) {
                root = node;
                root.left = null;
                root.right = null;
            } else {
                prevNode = TempNode = root;
                while (true) {
                    while (TempNode != null && TempNode.value >= array[i]) {
                        prevNode = TempNode;
                        TempNode = TempNode.left;
                    }
                    if (prevNode.left == null && prevNode.value >= array[i]) {
                        prevNode.left = node;
                        break;
                    }
                    while (TempNode != null && TempNode.value < array[i]) {
                        prevNode = TempNode;
                        TempNode = TempNode.right;
                    }
                    if (prevNode.right == null && prevNode.value < array[i]) {
                        prevNode.right = node;
                        break;
                    }
                }
            }
        }

        printInorder(root);
        System.out.println();
        printPreorder(root);
        System.out.println();
        printPostorder(root);
    }

    private static class TreeNodes {
        int value;
        TreeNodes left;
        TreeNodes right;

        public TreeNodes(int value) {
            this.value = value;
        }
    }
}
