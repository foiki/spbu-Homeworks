package group144.kireev;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void sizeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertEquals(0, tree.size());
        tree.add(5);
        assertEquals(1, tree.size());
    }

    @Test
    void containsTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        assertFalse(tree.contains(2));
        assertTrue(tree.contains(1));
    }

    @Test
    void isEmptyTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        assertFalse(tree.isEmpty());
        tree.remove(5);
        assertTrue(tree.isEmpty());
    }

    @Test
    void addTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        tree.add(1);
        tree.add(2);
        tree.add(2);
        tree.add(3);
        assertEquals("(1 null (2 null (3 null null)))", tree.toString());
        tree.add(4);
        tree.add(5);
        assertEquals("(1 null (2 null (3 null (4 null (5 null null)))))", tree.toString());
    }

    @Test
    void removeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.remove(3);
        assertEquals("(4 (1 null (2 null null)) (5 null (6 null null)))", tree.toString());
    }

    @Test
    void iteratorTest() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("somebody");
        tree.add("told");
        tree.add("me");
        Iterator<String> iterator = tree.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("me", iterator.next());
        assertEquals("somebody", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("told", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorEmptyTreeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Iterator<Integer> iterator = tree.iterator();
        assertFalse(iterator.hasNext());
        for (int i = 0; i < 10; i++) {
            assertNull(iterator.next());
        }
    }

    @Test
    void toArrayTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(3);
        assertArrayEquals(new Integer[]{1, 2, 3, 5}, tree.toArray());
    }

    @Test
    void containsAllTest() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("a");
        tree.add("b");
        tree.add("c");
        assertTrue(tree.containsAll(Arrays.asList("a", "c")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g")));
    }

    @Test
    void addAllTest() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.contains("c"));
        assertTrue(tree.containsAll(Arrays.asList("a", "c", "e")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g", "h")));
        assertTrue(tree.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    void removeAllTest() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.contains("c"));
        assertTrue(tree.containsAll(Arrays.asList("a", "c", "e")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g", "h")));
        assertTrue(tree.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    void retainAll() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.retainAll(Arrays.asList("a", "e")));
        assertFalse(tree.retainAll(Arrays.asList("a", "e", "h")));
        assertTrue(tree.retainAll(Arrays.asList("c", "d")));
        assertEquals(1, tree.size());
    }
}