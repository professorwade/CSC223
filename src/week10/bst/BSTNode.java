package week10.bst;

class BSTNode {
    public int key;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int nodeKey) {
        this(nodeKey, null, null);
    }

    public BSTNode(int nodeKey, BSTNode leftChild, BSTNode rightChild) {
        key = nodeKey;
        left = leftChild;
        right = rightChild;
    }
}
