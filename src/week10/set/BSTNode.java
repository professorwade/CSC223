package week10.set;

public class BSTNode {
    public int data;
    public BSTNode parent;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int data, BSTNode parent, BSTNode left, BSTNode right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public BSTNode(int data, BSTNode parent) {
        this(data, parent, null, null);
    }

    public int count() {
        int leftCount = 0;
        int rightCount = 0;
        if (left != null) {
            leftCount = left.count();
        }
        if (right != null) {
            rightCount = right.count();
        }
        return 1 + leftCount + rightCount;
    }

    public BSTNode getSuccessor() {
        // Successor resides in right subtree, if present
        if (right != null) {
            BSTNode successor = right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        // Otherwise the successor is up the tree
        // Traverse up the tree until a parent is encountered from the left
        BSTNode node = this;
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public void replaceChild(BSTNode currentChild, BSTNode newChild) {
        if (currentChild == left) {
            left = newChild;
            if (left != null) {
                left.parent = this;
            }
        }
        else if (currentChild == right) {
            right = newChild;
            if (right != null) {
                right.parent = this;
            }
        }
    }
}