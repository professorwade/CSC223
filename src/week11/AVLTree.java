package week11;

import java.io.PrintStream;

class AVLTree {
    private AVLNode root;

    private void insertNode(AVLNode node) {
        // Check if tree is empty
        if (root == null) {
            root = node;
        }
        else {
            // Step 1 - do a regular binary search tree insert
            AVLNode currentNode = root;
            while (currentNode != null) {
                // Choose to go left or right
                if (node.getKey() < currentNode.getKey()) {
                    // Go left. If left child is null, insert new node here
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(node);
                        currentNode = null;
                    }
                    else {
                        // Go left and do the loop again
                        currentNode = currentNode.getLeft();
                    }
                }
                else {
                    // Go right. If right child is null, insert new node here
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(node);
                        currentNode = null;
                    }
                    else {
                        // Go right and iterate
                        currentNode = currentNode.getRight();
                    }
                }
            }

            // Step 2 - Rebalance along path from new node's parent up to root
            node = node.getParent();
            while (node != null) {
                rebalance(node);
                node = node.getParent();
            }
        }
    }

    // Updates the given node's height and rebalances the subtree if
    // the balancing factor is now -2 or +2. Rebalancing is done by
    // performing a rotation.
    private void rebalance(AVLNode node) {
        // First update this node's height
        node.updateHeight();

        // Check for an imbalance
        if (node.getBalance() == -2) {
            // The subtree is too big to the right
            if (node.getRight().getBalance() == 1) {
                // Double rotation case: First rotate right on right child
                rotateRight(node.getRight());
            }

            // A left rotation will now make the subtree balanced
            rotateLeft(node);
        }
        else if (node.getBalance() == 2) {
            // The subtree is too big to the left
            if (node.getLeft().getBalance() == -1) {
                // Double rotation case: First rotate left on left child
                rotateLeft(node.getLeft());
            }

            // Rotate right to make the subtree balanced
            rotateRight(node);
        }
    }

    private boolean removeNode(AVLNode nodeToRemove) {
        // Base case
        if (nodeToRemove == null) {
            return false;
        }

        // nodeToRemove's parent is needed for rebalancing
        AVLNode parent = nodeToRemove.getParent();

        // Case 1: Internal node with 2 children
        if (nodeToRemove.getLeft() != null && nodeToRemove.getRight() != null) {
            // Find successor
            AVLNode successorNode = nodeToRemove.getRight();
            while (successorNode.getLeft() != null) {
                successorNode = successorNode.getLeft();
            }

            // Copy the value from the node
            nodeToRemove.setKey(successorNode.getKey());

            // Recursively remove successor
            removeNode(successorNode);

            // Nothing left to do since the recursive call will have rebalanced
            return true;
        }

        // Case 2: Root node (with 1 or 0 children)
        else if (nodeToRemove == root) {
            if (nodeToRemove.getLeft() != null) {
                root = nodeToRemove.getLeft();
            }
            else {
                root = nodeToRemove.getRight();
            }

            if (root != null) {
                root.setParent(null);
            }

            return true;
        }

        // Case 3: Internal with left child only
        else if (nodeToRemove.getLeft() != null) {
            parent.replaceChild(nodeToRemove, nodeToRemove.getLeft());
        }

        // Case 4: Internal with right child only OR leaf
        else {
            parent.replaceChild(nodeToRemove, nodeToRemove.getRight());
        }

        // Anything that was below nodeToRemove that has persisted is already
        // correctly balanced, but ancestors of nodeToRemove may need rebalancing
        AVLNode nodeToRebalance = parent;
        while (nodeToRebalance != null) {
            rebalance(nodeToRebalance);
            nodeToRebalance = nodeToRebalance.getParent();
        }

        return true;
    }

    // Performs a left rotation at the given node. Returns subtree's new root.
    private void rotateLeft(AVLNode node) {
        // Get references to node's parent, right child, and right child's left child
        AVLNode parent = node.getParent();
        AVLNode rightChild = node.getRight();
        AVLNode rightLeftChild = rightChild.getLeft();

        // First reassign node's right child
        node.setRight(rightLeftChild);

        // Next, reassign former right child's left child
        rightChild.setLeft(node);

        // Lastly, replace parent's child or reassign root if parent is null
        if (parent != null) {
            parent.replaceChild(node, rightChild);
        }
        else { // node is root
            root = rightChild;
            root.setParent(null);
        }
    }

    // Performs a right rotation at the given node. Returns subtree's new root.
    private void rotateRight(AVLNode node) {
        // Get references to node's parent, left child, and left child's right child
        AVLNode parent = node.getParent();
        AVLNode leftChild = node.getLeft();
        AVLNode leftRightChild = leftChild.getRight();

        // First reassign node's left child
        node.setLeft(leftRightChild);

        // Next, reassign former left child's right child
        leftChild.setRight(node);

        // Lastly, replace parent's child or reassign root if parent is null
        if (parent != null) {
            parent.replaceChild(node, leftChild);
        }
        else {
            root = leftChild;
            root.setParent(null);
        }
    }

    // Searches for a node with a matching key. Does a regular
    // binary search tree search operation. Returns the node with the
    // matching key, or null if no matching key exists in the tree.
    private AVLNode search(int desiredKey) {
        AVLNode currentNode = root;
        while (currentNode != null) {
            // Return the node if the key matches
            if (currentNode.getKey() == desiredKey) {
                return currentNode;
            }

            // Navigate left if search key < node's key
            else if (desiredKey < currentNode.getKey()) {
                currentNode = currentNode.getLeft();
            }

            // Navigate right if search key > node's key
            else {
                currentNode = currentNode.getRight();
            }
        }

        // Key not found
        return null;
    }

    public boolean contains(int key) {
        return search(key) != null;
    }

    public AVLNode getRoot() {
        return root;
    }

    public boolean insertKey(int key) {
        if (contains(key)) {
            return false;
        }

        insertNode(new AVLNode(key));
        return true;
    }

    public void printTree(PrintStream out) {
        out.println(AVLPrint.treeToString(root));
    }

    // Attempts to remove a node with a matching key. If no node has a matching
    // key then nothing is done and false is returned; otherwise the node is
    // removed and true is returned.
    public boolean removeKey(int key) {
        AVLNode node = search(key);
        if (node == null) {
            return false;
        }
        return removeNode(node);
    }
}
