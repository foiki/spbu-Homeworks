package group144.kireev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> implements Collection<T>, Iterable<T> {
    private Node root = null;
    private int size = 0;

    /**
     * @return number of elements in the collection
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return if collection is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element to check for contains
     * @return if the object contains in collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object element) {
        return !isEmpty() && root.contains((T) element);
    }

    /**
     * @return tree iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    /**
     * Method adding elements from the collection to array
     * @param array to add elements
     * @param <T1> parameter of array elements
     * @return array with elements from the collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T1>T1[] toArray(T1[] array) {
        ArrayList<T1> result = new ArrayList<>();
        for (T tmp : this) {
            result.add((T1) tmp);
        }
        return result.toArray(array);
    }

    /**
     * @return elements of collection as array of Objects in increasing order
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    /**
     * Method adding element to the collection
     * @param value element to add
     * @return if the element will be added
     */
    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            ++size;
            return true;
        }
        return root.add(value);
    }

    /**
     * Method removing element from the collection
     * @param value to remove from collection
     * @return if the element will be removed
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object value) {
        return !isEmpty() && root.remove((T) value);
    }

    /**
     * @param collection to check for contains
     * @return if Tree contains every element from the collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        boolean result = true;
        for (Object tmp : collection) {
            result &= contains(tmp);
        }
        return result;
    }

    /**
     * @param collection with elements to add
     * @return if all elements will be added
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = true;
        for (T tmp : collection) {
            result &= add(tmp);
        }
        return result;
    }

    /**
     * @param collection with elements to remove
     * @return if all elements will be removed
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = true;
        for (Object tmp : collection) {
            result &= remove(tmp);
        }
        return result;
    }

    /**
     * @param collection with elements to retain
     * @return if this collection will be changed
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = false;
        for (Object tmp : collection) {
            if (contains(tmp)) {
                remove(tmp);
                result = true;
            }
        }
        return result;
    }

    /**
     * Clear the collection
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return String representation of the collection
     */
    @Override
    public String toString() {
        return isEmpty() ? "null" : root.toString();
    }

    /**
     * Methods gets all Nodes from current subtree to the List
     * @param node subtree to get Nodes
     * @param elements List to add elements
     * @return List with elements
     */
    private ArrayList<Node> getAll(Node node, ArrayList<Node> elements) {
        if (node.left != null) {
            getAll(node.left, elements);
        }
        elements.add(node);
        if (node.right != null) {
            getAll(node.right, elements);
        }
        return elements;
    }

    /** Class realizing the Binary Search Tree Iterator */
    private class BSTIterator implements Iterator<T> {
        private Node current = null;
        private ArrayList<Node> uncheckedElements = new ArrayList<>();

        private BSTIterator() {
            if (BinarySearchTree.this.size != 0) {
                BinarySearchTree.this.getAll(root, uncheckedElements);
                current = uncheckedElements.get(0);
            }
        }

        /**
         * @return if the next element exist
         */
        @Override
        public boolean hasNext() {
            return !uncheckedElements.isEmpty() && treeContainsAtLeastOneElement();
        }

        /**
         * @return if the List contains at least one element from the tree
         */
        private boolean treeContainsAtLeastOneElement() {
            for (Node tmp : uncheckedElements) {
                if (BinarySearchTree.this.contains(tmp.value)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * @return value of next element, null if it does not exist
         */
        @Override
        public T next() {
            if (uncheckedElements.size() == 0) {
                return null;
            }
            for (Node tmp : uncheckedElements) {
                if (BinarySearchTree.this.contains(tmp.value)) {
                    uncheckedElements.remove(tmp);
                    return tmp.value;
                }
            }
            return null;
        }
    }

    /**
     * Class describes Node of the Binary Tree
     */
    private class Node {
        private T value;
        private Node parent;
        private Node left = null;
        private Node right = null;

        private Node(T value) {
            this.value = value;
            this.parent = null;
        }

        private Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        /**
         * Method adds an element with a specified value
         * @param value of new element
         */
        private boolean add(T value) {
            if (value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = new Node(value, this);
                    ++size;
                    return true;
                }
                left.add(value);
            } else  if (value.compareTo(this.value) > 0) {
                if (right == null) {
                    right = new Node(value, this);
                    ++size;
                    return true;
                }
                right.add(value);
            }
            return false;
        }

        /**
         * Method removes an element with a specified value
         * @param value of element to remove
         */
        private boolean remove(T value) {
            boolean result = false;
            if (value.compareTo(this.value) < 0) {
                result = left != null && left.remove(value);
            } else if (value.compareTo(this.value) > 0) {
                result = right != null && right.remove(value);
            } else {
                --size;
                this.remove();
            }
            return result;
        }

        /**
         * Method removing current Node
         */
        private void remove() {
            if (left != null && right != null) {
                Node newNode = this.findMinimalInRightSubtree();
                value = newNode.value;
                changeNode(newNode);
            } else if (left != null) {
                changeNode(left);
            } else if (right != null) {
                changeNode(right);
            } else {
                changeNode(null);
            }
        }

        /**
         * @return Node with minimum value in the subtree
         */
        private Node findMinimalInRightSubtree() {
            Node current = this.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        private void changeNode(Node newNode) {
            if (newNode == null) {
                if (parent == null) {
                    root = null;
                } else {
                    if (equals(parent.left)) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
                return;
            }
            value = newNode.value;
            if (newNode.equals(newNode.parent.left)) {
                newNode.parent.left = newNode.left;
            } else {
                newNode.parent.right = newNode.right;
            }
        }

        /**
         * @param element to check for contains
         * @return if the object contains in the subtree
         */
        private boolean contains(T element) {
            if (value.equals(element)) {
                return true;
            }
            if (value.compareTo(element) > 0) {
                return left != null && left.contains(element);
            }
            return right != null && right.contains(element);
        }

        /**
         * @return String representation of the subtree
         */
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("(").append(value.toString()).append(" ");
            result.append(left == null ? "null" : left.toString()).append(" ");
            result.append(right == null ? "null" : right.toString()).append(")");
            return result.toString();
        }

    }
}