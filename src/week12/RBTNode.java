package week12;

class RBTNode {
    public enum Color {
        RED, BLACK
    }

    private int key;
    private RBTNode parent;
    private RBTNode left;
    private RBTNode right;
    private Color color;

    public RBTNode(int nodeKey, RBTNode parentNode, boolean isRed) {
        this(nodeKey, parentNode, isRed, null, null);
    }

    public RBTNode(int nodeKey, RBTNode parentNode, boolean isRed, RBTNode leftChild, RBTNode rightChild) {
        key = nodeKey;
        parent = parentNode;
        left = leftChild;
        right = rightChild;
        color = isRed ? Color.RED : Color.BLACK;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public RBTNode getParent() {
        return parent;
    }

    public void setParent(RBTNode parent) {
        this.parent = parent;
    }

    public RBTNode getLeft() {
        return left;
    }

    public RBTNode getRight() {
        return right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Returns true if both child nodes are black. A null child is considered
    // to be black.
    public boolean areBothChildrenBlack() {
        if (left != null && left.isRed()) {
            return false;
        }
        if (right != null && right.isRed()) {
            return false;
        }
        return true;
    }

    public int count() {
        int count = 1;
        if (left != null) {
            count += left.count();
        }
        if (right != null) {
            count += right.count();
        }
        return count;
    }

    public RBTNode getGrandparent() {
        if (parent == null) {
            return null;
        }
        return parent.parent;
    }

    // Gets this node's predecessor from the left child subtree
    // Precondition: This node's left child is not null
    public RBTNode getPredecessor() {
        RBTNode node = left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // Returns this node's sibling, or null if this node does not have a sibling
    public RBTNode getSibling() {
        if (parent != null) {
            if (this == parent.left) {
                return parent.right;
            }
            return parent.left;
        }
        return null;
    }

    // Returns the uncle of this node
    public RBTNode getUncle() {
        RBTNode grandparent = getGrandparent();
        if (grandparent == null) {
            return null;
        }
        if (grandparent.left == parent) {
            return grandparent.right;
        }
        return grandparent.left;
    }

    // Returns true if this node is black, false otherwise
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    // Returns true if this node is red, false otherwise
    public boolean isRed() {
        return color == Color.RED;
    }

    // Replaces one of this node's children with a new child
    public boolean replaceChild(RBTNode currentChild, RBTNode newChild) {
        if (left == currentChild) {
            setLeft(newChild);
            return true;
        }
        else if (right == currentChild) {
            setRight(newChild);
            return true;
        }
        return false;
    }

    // Sets this node's left child
    public void setLeft(RBTNode newChild) {
        left = newChild;

        if (left != null) {
            left.parent = this;
        }
    }

    // Sets this node's right child
    public void setRight(RBTNode newChild) {
        right = newChild;

        if (right != null) {
            right.parent = this;
        }
    }
}