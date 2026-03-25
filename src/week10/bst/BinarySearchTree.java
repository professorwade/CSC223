package week10.bst;
class BinarySearchTree {
    private BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    public BSTNode getRoot() {
        return root;
    }

    public boolean contains(int key) {
        return search(key) != null;
    }

    public BSTNode search(int searchKey) {
        BSTNode currentNode = root;
        while (currentNode != null) {
            // Return the node if the key matches
            if (currentNode.key == searchKey) {
                return currentNode;
            }

            // Navigate left if search key < node's key
            else if (searchKey < currentNode.key) {
                currentNode = currentNode.left;
            }

            // Navigate right if search key > node's key
            else {
                currentNode = currentNode.right;
            }
        }

        // Key not found in the tree
        return null;
    }

    public void insertNode(BSTNode node) {
        // Check if tree is empty
        if (root == null) {
            root = node;
        }
        else {
            BSTNode currentNode = root;
            while (currentNode != null) {
                if (node.key < currentNode.key) {
                    // If no left child exists, add the new node
                    // here; otherwise repeat from the left child.
                    if (currentNode.left == null) {
                        currentNode.left = node;
                        currentNode = null;
                    }
                    else {
                        currentNode = currentNode.left;
                    }
                }
                else {
                    // If no right child exists, add the new node
                    // here; otherwise repeat from the right child.
                    if (currentNode.right == null) {
                        currentNode.right = node;
                        currentNode = null;
                    }
                    else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    public boolean insertKey(int key) {
        // Duplicate keys not allowed
        if (contains(key)) {
            return false;
        }

        // Create and insert a new node for the key and return true
        insertNode(new BSTNode(key));
        return true;
    }

    public boolean remove(int key) {
        BSTNode parent = null;
        BSTNode currentNode = root;

        // Search for the node
        while (currentNode != null) {
            // Check if currentNode has a matching key
            if (currentNode.key == key) {
                if (currentNode.left == null && currentNode.right == null) {
                    // Remove leaf

                    if (parent == null) { // Node is root
                        root = null;
                    }
                    else if (parent.left == currentNode) {
                        parent.left = null;
                    }
                    else {
                        parent.right = null;
                    }

                    return true; // Node found and removed
                }
                else if (currentNode.left != null && currentNode.right == null) {
                    // Remove node with only left child

                    if (parent == null) { // Node is root
                        root = currentNode.left;
                    }
                    else if (parent.left == currentNode) {
                        parent.left = currentNode.left;
                    }
                    else {
                        parent.right = currentNode.left;
                    }

                    return true; // Node found and removed
                }
                else if (currentNode.left == null && currentNode.right != null) {
                    // Remove node with only right child

                    if (parent == null) { // Node is root
                        root = currentNode.right;
                    }
                    else if (parent.left == currentNode) {
                        parent.left = currentNode.right;
                    }
                    else {
                        parent.right = currentNode.right;
                    }

                    return true; // Node found and removed
                }
                else {
                    // Remove node with two children

                    // Find successor (leftmost child of right subtree)
                    BSTNode successor = currentNode.right;
                    while (successor.left != null) {
                        successor = successor.left;
                    }

                    currentNode.key = successor.key; // Copy successor to current node
                    parent = currentNode;
                    currentNode = currentNode.right; // Remove successor from right subtree
                    key = successor.key;             // Loop continues with new key
                }
            }
            else if (currentNode.key < key) {
                // Search right

                parent = currentNode;
                currentNode = currentNode.right;
            }
            else {
                // Search left

                parent = currentNode;
                currentNode = currentNode.left;
            }
        }

        return false; // Node not found
    }
}
