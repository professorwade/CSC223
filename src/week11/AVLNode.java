package week11;
class AVLNode {
    int key;
    AVLNode parent;
    AVLNode left;
    AVLNode right;
    int height;

    // Constructs an AVLNode with the given key
    public AVLNode(int nodeKey) {
        key = nodeKey;
        parent = null;
        left = null;
        right = null;
        height = 0;
    }

    // Calculates this node's balance factor, defined as:
    // height(left subtree) - height(right subtree)
    public int getBalance() {
        // Get current height of left subtree, or -1 if null
        int leftHeight = -1;
        if (left != null) {
            leftHeight = left.height;
        }

        // Get current height of right subtree, or -1 if null
        int rightHeight = -1;
        if (right != null) {
            rightHeight = right.height;
        }

        // Calculate the balance factor.
        return leftHeight - rightHeight;
    }

    // Returns this node's key
    public int getKey() {
        return key;
    }

    // Returns this node's left child node
    public AVLNode getLeft() {
        return left;
    }

    // Returns this node's parent node
    public AVLNode getParent() {
        return parent;
    }

    // Returns this node's right child node
    public AVLNode getRight() {
        return right;
    }

    // Replaces a current child with a new child. Determines if the current
    // child is the left or right, then calls SetLeft() or SetRight() with the
    // new node appropriately. Returns true if the new child is assigned, false
    // otherwise.
    public boolean replaceChild(AVLNode currentChild, AVLNode newChild) {
        if (left == currentChild) {
            setLeft(newChild);
            return true;
        }
        else if (right == currentChild) {
            setRight(newChild);
            return true;
        }

        // currentChild is not a child of this node
        return false;
    }

    // Reassigns this node's key with the new key
    public void setKey(int newKey) {
        key = newKey;
    }

    // Reassigns this node's left child with the new child. If non-null, the
    // new left child's parent is assigned with a reference to this node.
    // After assigning the new child, this node's height is updated.
    public void setLeft(AVLNode newLeftChild) {
        // Assign new left child
        left = newLeftChild;

        // If new child is non-null, assign parent
        if (left != null) {
            left.parent = this;
        }

        // A new child may change this node's height
        updateHeight();
    }

    // Reassigns this node's parent node with the new parent
    public void setParent(AVLNode newParent) {
        parent = newParent;
    }

    // Reassigns this node's right child with the new child. If non-null, the
    // new right child's parent is assigned with a reference to this node.
    // After assigning the new child, this node's height is updated.
    public void setRight(AVLNode newRightChild) {
        // Assign new right child
        right = newRightChild;

        // If new child is non-null, assign parent
        if (right != null) {
            right.parent = this;
        }

        // A new child may change this node's height
        updateHeight();
    }

    // Recalculates the current height of the subtree rooted at this node.
    // Usually called after a subtree has been modified.
    public void updateHeight() {
        // Get current height of left subtree, or -1 if null
        int leftHeight = -1;
        if (left != null) {
            leftHeight = left.height;
        }

        // Get current height of right subtree, or -1 if null
        int rightHeight = -1;
        if (right != null) {
            rightHeight = right.height;
        }

        // Assign height with calculated node height
        height = ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
    }
}