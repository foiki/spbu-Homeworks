package group144.kireev;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void sizeTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        assertEquals(0, tree.size());
        tree.add(5);
        assertEquals(1, tree.size());
    }

    @Test
    void containsTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(1);
        assertFalse(tree.contains(2));
        assertTrue(tree.contains(1));
    }

    @Test
    void isEmptyTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(5);
        assertFalse(tree.isEmpty());
        tree.remove(5);
        assertTrue(tree.isEmpty());
    }

    @Test
    void addTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(1);
        tree.add(1);
        tree.add(2);
        tree.add(2);
        tree.add(3);
        assertEquals("(2 (1 null null) (3 null null))", tree.toString());
        tree.add(4);
        tree.add(5);
        assertEquals("(2 (1 null null) (4 (3 null null) (5 null null)))", tree.toString());
    }

    @Test
    void removeTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.remove(2);
        assertEquals("(3 (1 null null) (4 null (5 null null)))", tree.toString());
    }

    @Test
    void iteratorTest() {
        AVLTree<String> tree = new AVLTree<>();
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
    void toArrayTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(1);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(3);
        assertArrayEquals(new Integer[]{1, 2, 3, 5}, tree.toArray());
    }

    @Test
    void containsAllTest() {
        AVLTree<String> tree = new AVLTree<>();
        tree.add("a");
        tree.add("b");
        tree.add("c");
        assertTrue(tree.containsAll(Arrays.asList("a", "c")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g")));
    }

    @Test
    void addAllTest() {
        AVLTree<String> tree = new AVLTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.contains("c"));
        assertTrue(tree.containsAll(Arrays.asList("a", "c", "e")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g", "h")));
        assertTrue(tree.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    void removeAllTest() {
        AVLTree<String> tree = new AVLTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.contains("c"));
        assertTrue(tree.containsAll(Arrays.asList("a", "c", "e")));
        assertFalse(tree.containsAll(Arrays.asList("a", "g", "h")));
        assertTrue(tree.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    void retainAll() {
        AVLTree<String> tree = new AVLTree<>();
        tree.addAll(Arrays.asList("abcde".split("")));
        assertTrue(tree.retainAll(Arrays.asList("a", "e")));
        assertFalse(tree.retainAll(Arrays.asList("a", "e", "h")));
        assertTrue(tree.retainAll(Arrays.asList("c", "d")));
        assertEquals(1, tree.size());
    }

}