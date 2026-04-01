package week12;

class RedBlackTree {
    private RBTNode root;

    public RedBlackTree() {
        root = null;
    }

    public RBTNode getRoot() {
        return root;
    }

    public int getLength() {
        if (root == null) {
            return 0;
        }
        return root.count();
    }

    // Returns the height of this tree
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(RBTNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeightRecursive(node.getLeft());
        int rightHeight = getHeightRecursive(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean insertKey(int key) {
        if (contains(key)) {
            return false;
        }

        RBTNode newNode = new RBTNode(key, null, true);
        insertNode(newNode);
        return true;
    }

    private void insertNode(RBTNode node) {
        // Begin with normal BST insertion
        if (root == null) {
            // Special case for root
            root = node;
        }
        else {
            RBTNode currentNode = root;
            while (currentNode != null) {
                if (node.getKey() < currentNode.getKey()) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(node);
                        break;
                    }
                    else {
                        currentNode = currentNode.getLeft();
                    }
                }
                else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(node);
                        break;
                    }
                    else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }

        // Color the node red, then balance
        node.setColor(RBTNode.Color.RED);
        insertionBalance(node);
    }

    private void insertionBalance(RBTNode node) {
        // If node is the tree's root, then color node black and return
        if (node.getParent() == null) {
            node.setColor(RBTNode.Color.BLACK);
            return;
        }

        // If parent is black, then return without any changes
        if (node.getParent().isBlack()) {
            return;
        }

        // References to parent, grandparent, and uncle are needed for remaining operations
        RBTNode parent = node.getParent();
        RBTNode grandparent = node.getGrandparent();
        RBTNode uncle = node.getUncle();

        // If parent and uncle are both red, then color parent and uncle black, color grandparent
        // red, recursively balance  grandparent, then return
        if (uncle != null && uncle.isRed()) {
            parent.setColor(RBTNode.Color.BLACK);
            uncle.setColor(RBTNode.Color.BLACK);
            grandparent.setColor(RBTNode.Color.RED);
            insertionBalance(grandparent);
            return;
        }

        // If node is parent's right child and parent is grandparent's left child, then rotate left
        // at parent, update node and parent to point to parent and grandparent, respectively
        if (node == parent.getRight() && parent == grandparent.getLeft()) {
            rotateLeft(parent);
            node = parent;
            parent = node.getParent();
        }
        // Else if node is parent's left child and parent is grandparent's right child, then rotate
        // right at parent, update node and parent to point to parent and grandparent, respectively
        else if (node == parent.getLeft() && parent == grandparent.getRight()) {
            rotateRight(parent);
            node = parent;
            parent = node.getParent();
        }

        // Color parent black and grandparent red
        parent.setColor(RBTNode.Color.BLACK);
        grandparent.setColor(RBTNode.Color.RED);

        // If node is parent's left child, then rotate right at grandparent, otherwise rotate left
        // at grandparent
        if (node == parent.getLeft()) {
            rotateRight(grandparent);
        }
        else {
            rotateLeft(grandparent);
        }
    }

    // Performs a left rotation at the given node. Returns the
    // subtree's new root.
    private void rotateLeft(RBTNode node) {
        // Get the right child's left child
        RBTNode rightLeftChild = node.getRight().getLeft();

        // Step 1 - the right child moves up to the node's position.
        // node is detached from the tree and reattached later.
        if (node.getParent() != null) {
            node.getParent().replaceChild(node, node.getRight());
        }
        else { // node is root
            root = node.getRight();
            root.setParent(null);
        }

        // Step 2 - node becomes the left child of the node's right child,
        // temporarily detaching rightLeftChild from the tree
        node.getRight().setLeft(node);

        // Step 3 - reattach rightLeftChild as the node's right child
        node.setRight(rightLeftChild);
    }

    // Performs a right rotation at the given node. Returns the
    // subtree's new root.
    private void rotateRight(RBTNode node) {
        // Define a convenience pointer to the left child of the
        // right child.
        RBTNode leftRightChild = node.getLeft().getRight();

        // Step 1 - the left child moves up to the node's position.
        // node is detached from the tree and reattached later.
        if (node.getParent() != null) {
            node.getParent().replaceChild(node, node.getLeft());
        }
        else { // node is root
            root = node.getLeft();
            root.setParent(null);
        }

        // Step 2 - node becomes the right child of the node's left child,
        // temporarily detaching leftRightChild from the tree
        node.getLeft().setRight(node);

        // Step 3 - reattach leftRightChild as the node's right child
        node.setLeft(leftRightChild);
    }

    private void bstRemove(int key) {
        RBTNode node = search(key);
        bstRemoveNode(node);
    }

    private void bstRemoveNode(RBTNode node) {
        if (node == null) {
            return;
        }

        // Case 1: Internal node with 2 children
        if (node.getLeft() != null && node.getRight() != null) {
            // Find successor
            RBTNode successorNode = node.getRight();
            while (successorNode.getLeft() != null) {
                successorNode = successorNode.getLeft();
            }

            // Copy successor's key
            int successorKey = successorNode.getKey();

            // Recursively remove successor
            bstRemoveNode(successorNode);

            // Set node's key to copied successor key
            node.setKey(successorKey);
        }

        // Case 2: Root node (with 1 or 0 children)
        else if (node == root) {
            if (node.getLeft() != null) {
                root = node.getLeft();
            }
            else {
                root = node.getRight();
            }

            // Make sure the new root, if non-null, has parent set to null
            if (root != null) {
                root.setParent(null);
            }
        }

        // Case 3: Internal with left child only
        else if (node.getLeft() != null) {
            node.getParent().replaceChild(node, node.getLeft());
        }

        // Case 4: Internal with right child OR leaf
        else {
            node.getParent().replaceChild(node, node.getRight());
        }
    }

    public boolean isNullOrBlack(RBTNode node) {
        if (node == null) {
            return true;
        }
        return node.isBlack();
    }

    public boolean isNotNullAndRed(RBTNode node) {
        if (node == null) {
            return false;
        }
        return node.isRed();
    }

    private void prepareForRemoval(RBTNode node) {
        if (tryCase1(node)) {
            return;
        }

        RBTNode sibling = node.getSibling();
        if (tryCase2(node, sibling)) {
            sibling = node.getSibling();
        }
        if (tryCase3(node, sibling)) {
            return;
        }
        if (tryCase4(node, sibling)) {
            return;
        }
        if (tryCase5(node, sibling)) {
            sibling = node.getSibling();
        }
        if (tryCase6(node, sibling)) {
            sibling = node.getSibling();
        }

        sibling.setColor(node.getParent().getColor());
        node.getParent().setColor(RBTNode.Color.BLACK);
        if (node == node.getParent().getLeft()) {
            sibling.getRight().setColor(RBTNode.Color.BLACK);
            rotateLeft(node.getParent());
        }
        else {
            sibling.getLeft().setColor(RBTNode.Color.BLACK);
            rotateRight(node.getParent());
        }
    }

    public boolean removeKey(int key) {
        RBTNode node = search(key);
        if (node != null) {
            removeNode(node);
            return true;
        }
        return false;
    }

    private void removeNode(RBTNode node) {
        if (node.getLeft() != null && node.getRight() != null) {
            RBTNode predecessorNode = node.getPredecessor();
            int predecessorKey = predecessorNode.getKey();
            removeNode(predecessorNode);
            node.setKey(predecessorKey);
            return;
        }

        if (node.isBlack()) {
            prepareForRemoval(node);
        }
        bstRemove(node.getKey());

        // One special case if the root was changed to red
        if (root != null && root.isRed()) {
            root.setColor(RBTNode.Color.BLACK);
        }
    }

    public boolean contains(int key) {
        return search(key) != null;
    }

    // Searches for a node with a matching key. Does a regular
    // binary search tree search operation. Returns the node with the
    // matching key if it exists in the tree, or null if no matching
    // key in the tree exists.
    private RBTNode search(int desiredKey) {
        RBTNode currentNode = root;
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

        // Key not found in the tree
        return null;
    }

    private boolean tryCase1(RBTNode node) {
        if (node.isRed() || node.getParent() == null) {
            return true;
        }
        return false; // node case 1
    }

    private boolean tryCase2(RBTNode node, RBTNode sibling) {
        if (sibling.isRed()) {
            node.getParent().setColor(RBTNode.Color.RED);
            sibling.setColor(RBTNode.Color.BLACK);
            if (node == node.getParent().getLeft()) {
                rotateLeft(node.getParent());
            }
            else {
                rotateRight(node.getParent());
            }
            return true;
        }
        return false; // not case 2
    }

    private boolean tryCase3(RBTNode node, RBTNode sibling) {
        if (node.getParent().isBlack() && sibling.areBothChildrenBlack()) {
            sibling.setColor(RBTNode.Color.RED);
            prepareForRemoval(node.getParent());
            return true;
        }
        return false; // not case 3
    }

    private boolean tryCase4(RBTNode node, RBTNode sibling) {
        if (node.getParent().isRed() && sibling.areBothChildrenBlack()) {
            node.getParent().setColor(RBTNode.Color.BLACK);
            sibling.setColor(RBTNode.Color.RED);
            return true;
        }
        return false; // not case 4
    }

    private boolean tryCase5(RBTNode node, RBTNode sibling) {
        if (isNotNullAndRed(sibling.getLeft())) {
            if (isNullOrBlack(sibling.getRight())) {
                if (node == node.getParent().getLeft()) {
                    sibling.setColor(RBTNode.Color.RED);
                    sibling.getLeft().setColor(RBTNode.Color.BLACK);
                    rotateRight(sibling);
                    return true;
                }
            }
        }
        return false; // not case 5
    }

    private boolean tryCase6(RBTNode node, RBTNode sibling) {
        if (isNullOrBlack(sibling.getLeft())) {
            if (isNotNullAndRed(sibling.getRight())) {
                if (node == node.getParent().getRight()) {
                    sibling.setColor(RBTNode.Color.RED);
                    sibling.getRight().setColor(RBTNode.Color.BLACK);
                    rotateLeft(sibling);
                    return true;
                }
            }
        }
        return false; // not case 6
    }
}