public class RedBlackTree extends BinaryTree {


    private class Node extends BinNode {
        String color;
        Node left, right, parent;

        Node(String data) {
            super(data);
            color = "RED";
        }
    }

    private final Node NIL;
    private Node root;

    public RedBlackTree() {
        NIL = new Node("");
        NIL.color = "BLACK";
        NIL.left = NIL.right = NIL.parent = null;
        root = NIL;
    }

    public void insert(String data) {
        Node newNode = new Node(data);
        newNode.left = newNode.right = NIL;

        Node parent = null;
        Node current = root;

        while (current != NIL) {
            parent = current;
            if (data.compareTo(current.getData()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;

        if (parent == null) {
            root = newNode;
        } else if (data.compareTo(parent.getData()) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.color = "RED";

        if (newNode.parent == null) {
            newNode.color = "BLACK";
            return;
        }

        if (newNode.parent.parent == null) {
            return;
        }

        fixInsert(newNode);
    }

    //  help from g4g
    private void fixInsert(Node k) {
        while (k.parent.color.equals("RED")) {
            if (k.parent == k.parent.parent.left) {
                Node u = k.parent.parent.right;
                if (u.color.equals("RED")) {
                    k.parent.color = "BLACK";
                    u.color = "BLACK";
                    k.parent.parent.color = "RED";
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = "BLACK";
                    k.parent.parent.color = "RED";
                    rightRotate(k.parent.parent);
                }
            } else {
                Node u = k.parent.parent.left;
                if (u.color.equals("RED")) {
                    k.parent.color = "BLACK";
                    u.color = "BLACK";
                    k.parent.parent.color = "RED";
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = "BLACK";
                    k.parent.parent.color = "RED";
                    leftRotate(k.parent.parent);
                }
            }
            if (k == root) break;
        }
        root.color = "BLACK";
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.getData() + " ");
            inorderHelper(node.right);
        }
    }

    public boolean search(String key) {
        return searchHelper(root, key) != NIL;
    }

    private Node searchHelper(Node node, String key) {
        if (node == NIL || key.equals(node.getData())) {
            return node;
        }

        if (key.compareTo(node.getData()) < 0) {
            return searchHelper(node.left, key);
        } else {
            return searchHelper(node.right, key);
        }
    }



}
