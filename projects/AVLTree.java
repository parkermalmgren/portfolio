public class AVLTree extends BinaryTree {



    // Helper method to get height of a node
    private int height(BinNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }


    // Calculate balance factor
    private int getBalance(BinNode node) {
        if (node == null) return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    // right rotation
    private BinNode rotateRight(BinNode y) {
        BinNode x = y.getLeft();
        BinNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);


        return x;
    }

    // left rotatation
    private BinNode rotateLeft(BinNode x) {
        BinNode y = x.getRight();
        BinNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        return y;
    }

    public void insert(String data) {
        this.setRoot(insertAVL(this.getRoot(), data));
    }


    private BinNode insertAVL(BinNode node, String data) {
        if (node == null) {
            return new BinNode(data);
        }


        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insertAVL(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insertAVL(node.getRight(), data));
        } else {
            return node; // no duplicates
        }

        int balance = getBalance(node);

        // left Case
        if (balance > 1 && data.compareTo(node.getLeft().getData()) < 0) {
            return rotateRight(node);
        }

        //  right Case
        if (balance < -1 && data.compareTo(node.getRight().getData()) > 0) {
            return rotateLeft(node);
        }

        // left Right Case
        if (balance > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }


        // right Left Case
        if (balance < -1 && data.compareTo(node.getRight().getData()) < 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }
}
