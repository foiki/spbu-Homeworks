package group144.kireev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class realizing Collection as AVLTree
 * @param <T> Type of elements. Should be comparable
 */
public class AVLTree<T extends Comparable<T>> implements Collection<T> {
    private Node root = null;
    private int size = 0;

    /**
     * @return number of elements in the collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return if collection is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * @param object to check for contains
     * @return if the object contains to collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        return !isEmpty() && root.contains((T) object);
    }

    /**
     * @return tree iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new AVLTreeIterator();
    }

    /**
     * @return elements of collection as array of Objects in increasing order
     */
    @Override
    public Object[] toArray() {
        ArrayList<T> result = new ArrayList<>();
        if (root == null) {
            return null;
        } else {
            root.takeAll(result);
            return result.toArray();
        }
    }

    /**
     * @param array the array in which elements should be added
     * @param <T1> the type of stored elements
     * @return an array of tree elements
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
     * Method adding element to the tree. Stores each element only once
     * @param value of adding element
     * @return true if the element will be added to tree and else
     * if it has been already added
     */
    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        ++size;
        if (root == null) {
            root = new Node(value, null);
        } else {
            root.add(value);
        }
        return true;
    }

    /**
     * Method removing element to the tree
     * @param value of removing element
     * @return true if the element will be removed from tree and else
     * if it is not in the tree
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object value) {
        if (!contains(value)) {
            return false;
        }
        --size;
        root.remove((T) value);
        return true;
    }

    /**
     * @param collection to be checked for containment in this collection
     * @return if this collection contains all elements from stated collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        boolean result = true;
        for (Object element : collection) {
            result = result && contains(element);
        }
        return result;
    }

    /**
     * @param collection of elements to be added to this collection
     * @return if all elements from stated collection will be added
     * to this collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> collection) {
        boolean result = true;
        for (Object element : collection) {
            result = result && add((T) element);
        }
        return result;
    }

    /**
     * @param collection of elements to be removed from this collection
     * @return if all elements from stated collection will be removed
     * from this collection
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean result = true;
        for (Object element : collection) {
            result = result && remove(element);
        }
        return result;
    }

    /**
     * Removing all elements of stated collection from this collection
     * @param collection to get elements
     * @return true if something is deleted
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean result = false;
        for (Object element : collection) {
            if (contains(element)) {
                remove(element);
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return tree in a b c format
     */
    public String toString() {
        if (root != null) {
            return root.ABCFormat();
        } else {
            return "null";
        }
    }

    /** Class realizing the AVL Tree Iterator */
    private class AVLTreeIterator implements Iterator<T> {
        private ArrayList<T> elements;

        AVLTreeIterator() {
            elements = new ArrayList<>();
            if (root == null) {
                elements = null;
            } else {
                root.takeAll(elements);
            }
        }

        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        @Override
        public T next() {
            if (elements.size() == 0) {
                return null;
            }
            return elements.remove(0);
        }
    }

    /** Class releasing Node of AVLTree */
    private class Node {
        private T value;
        private int height = 1;
        private Node parent;
        private Node left = null;
        private Node right = null;

        Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        /** A method checks if the value is in the tree
         * @return true if value is in the tree and false if isn't
         * */
        private boolean contains(T value) {
            if (this.value.equals(value)) {
                return true;
            }
            if (value.compareTo(this.value) < 0) {
                return left != null && left.contains(value);
            }
            return right != null && right.contains(value);
        }

        /**
         * Method adding elements from tree to list
         * @param list in which to add elements of tree
         */
        private void takeAll(ArrayList<T> list) {
            if (left != null) {
                left.takeAll(list);
            }
            list.add(this.value);
            if (right != null) {
                right.takeAll(list);
            }
        }

        /**
         * Method adding element to the tree
         * @param value of adding element
         */
        private void add(T value) {
            if (value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = new Node(value, this);
                } else {
                    left.add(value);
                }
            } else {
                if (right == null) {
                    right = new Node(value, this);
                } else {
                    right.add(value);
                }
            }
            balance();
        }

        /**
         * Method removing element from tree
         * @param value of removing element
         */
        private void remove(T value) {
            if (value.compareTo(this.value) < 0) {
                left.remove(value);
                return;
            }
            if (value.compareTo(this.value) > 0) {
                right.remove(value);
                return;
            }
            if (left != null && right != null) {
                Node minRight = right;
                while(minRight.left != null) {
                    minRight = minRight.left;
                }
                changeNode(minRight);
            } else if (left != null) {
                changeNode(left);
            } else if (right != null) {
                changeNode(right);
            } else {
                changeNode(null);
            }
            balance();
        }

        /**
         * Method performs swap this Node to newNode
         * @param newNode the Node to which you want to swap
         */
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
                newNode.parent.left = null;
            } else {
                newNode.parent.right = null;
            }
        }

        /**
         * @return node in ABC format
         */
        private String ABCFormat() {
            StringBuilder result = new StringBuilder("(" + value.toString() + " ");
            if (left != null) {
                result.append(left.ABCFormat());
            } else {
                result.append("null");
            }
            result.append(" ");
            if (right != null) {
                result.append(right.ABCFormat());
            } else {
                result.append("null");
            }
            result.append(")");
            return result.toString();
        }

        /** A method performs right rotate */
        private void rotateRight() {
            Node pivot = left;
            if (pivot.right != null)
                pivot.right.parent = this;
            left = pivot.right;
            pivot.right = this;
            if (parent == null) {
                root = pivot;
            } else {
                if (equals(parent.left)) {
                    parent.left = pivot;
                } else {
                    parent.right = pivot;
                }
            }
            pivot.parent = parent;
            parent = pivot;
            this.updateHeight();
            pivot.updateHeight();
        }

        /** A method performs left rotate */
        private void rotateLeft() {
            Node pivot = right;
            if (pivot.left != null)
                pivot.left.parent = this;
            right = pivot.left;
            pivot.left = this;
            if (parent == null) {
                root = pivot;
            } else {
                if (equals(parent.right)) {
                    parent.right = pivot;
                } else {
                    parent.left = pivot;
                }
            }
            pivot.parent = parent;
            parent = pivot;
            this.updateHeight();
            pivot.updateHeight();
        }

        /** A method updating height of Node */
        private void updateHeight() {
            int heightLeft = (left == null) ? 0 : left.height;
            int heightRight = (right == null) ? 0 : right.height;
            height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
        }

        /**
         * A method performs balance of this Node
         */
        private void balance() {
            updateHeight();
            if (balanceFactor() == 2) {
                if (left.balanceFactor() < 0) {
                    left.rotateLeft();
                }
                right.rotateRight();
            } else if (balanceFactor() == -2) {
                if (right.balanceFactor() > 0) {
                    right.rotateRight();
                }
                rotateLeft();
            }
        }

        /**
         * @return calculate balance factor for this Node
         */
        private int balanceFactor() {
            return (left != null ? left.height : 0) - (right != null ? right.height : 0);
        }
    }
}
