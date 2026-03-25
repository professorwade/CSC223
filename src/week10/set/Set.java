package week10.set;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

class BSTIterator implements Iterator<Integer> {
    private BSTNode nextNode;

    public BSTIterator(BSTNode startNode) {
        nextNode = startNode;
    }

    public boolean hasNext() {
        return nextNode != null;
    }

    public Integer next() {
        Integer returnMe = null;
        if (nextNode != null) {
            returnMe = nextNode.data;
            nextNode = nextNode.getSuccessor();
        }
        return returnMe;
    }
}

public class Set implements Iterable<Integer> {
    private BSTNode root;

    public Set() {
        root = null;
    }

    public boolean add(Integer newElement) {
        if (contains(newElement)) {
            return false;
        }

        BSTNode newNode = new BSTNode(newElement, null);

        // Special case for empty set
        if (root == null) {
            root = newNode;
            return true;
        }

        BSTNode node = root;
        while (node != null) {
            if (newElement < node.data) {
                // Go left
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node.left = newNode;
                    newNode.parent = node;
                    node = null;
                }
            }
            else {
                // Go right
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    node.right = newNode;
                    newNode.parent = node;
                    node = null;
                }
            }
        }
        return true;
    }

    public boolean contains(Integer element) {
        return nodeSearch(element) != null;
    }

    public Set difference(Set otherSet) {
        Set result = new Set();
        for (Integer element : this) {
            if (!otherSet.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Set filter(Predicate<Integer> predicate) {
        Set result = new Set();
        for (Integer element : this) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Set intersection(Set otherSet) {
        Set result = new Set();
        for (Integer element : this) {
            if (otherSet.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Iterator<Integer> iterator() {
        // Special case for empty set
        if (root == null) {
            return new BSTIterator(null);
        }

        // Start the iterator at the minimum node
        BSTNode minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return new BSTIterator(minNode);
    }

    public Set map(Function<Integer, Integer> mapFunction) {
        Set result = new Set();
        for (Integer element : this) {
            Integer newElement = mapFunction.apply(element);
            result.add(newElement);
        }
        return result;
    }

    private BSTNode nodeSearch(Integer element) {
        // Search the BST
        BSTNode node = root;
        while (node != null) {
            // Compare node's data against the search element
            if (element.equals(node.data)) {
                return node;
            }
            else if (element > node.data) {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }
        return node;
    }

    public void remove(Integer element) {
        removeNode(nodeSearch(element));
    }

    private void removeNode(BSTNode nodeToRemove) {
        if (nodeToRemove == null) {
            return;
        }

        // Case 1: Internal node with 2 children
        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            BSTNode successor = nodeToRemove.getSuccessor();

            // Copy the data value from the successor
            int dataCopy = successor.data;

            // Remove successor
            removeNode(successor);

            // Replace nodeToRemove's data with successor data
            nodeToRemove.data = dataCopy;
        }

        // Case 2: Root node (with 1 or 0 children)
        else if (nodeToRemove == root) {
            if (nodeToRemove.left != null) {
                root = nodeToRemove.left;
            }
            else {
                root = nodeToRemove.right;
            }

            if (root != null) {
                root.parent = null;
            }
        }

        // Case 3: Internal node with left child only
        else if (nodeToRemove.left != null) {
            nodeToRemove.parent.replaceChild(nodeToRemove, nodeToRemove.left);
        }

        // Case 4: Internal node with right child only, or leaf node
        else {
            nodeToRemove.parent.replaceChild(nodeToRemove, nodeToRemove.right);
        }
    }

    public int size() {
        if (root == null) {
            return 0;
        }
        return root.count();
    }

    public Set union(Set otherSet) {
        Set result = new Set();
        for (Integer element : this) {
            result.add(element);
        }
        for (Integer element : otherSet) {
            result.add(element);
        }
        return result;
    }
}