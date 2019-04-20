package group144.kireev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    private Trie trie;

    @BeforeEach
    public void initialization() {
        trie = new Trie();
        trie.add("Somebody");
        trie.add("once");
        trie.add("told");
        trie.add("me");
        trie.add("the");
        trie.add("world");
        trie.add("is");
        trie.add("gonna");
        trie.add("roll");
        trie.add("me");
    }


    @Test
    void size() {
        assertEquals(9, trie.size());
    }

    @Test
    void add() {
        assertFalse(trie.add(null));
        assertFalse(trie.add("Somebody"));
        assertTrue(trie.add("Hello"));
    }

    @Test
    void contains() {
        assertTrue(trie.contains("me"));
        assertTrue(trie.contains("world"));
        assertFalse(trie.contains("Me"));
    }

    @Test
    void remove() {
        assertFalse(trie.remove(null));
        assertFalse(trie.remove("Me"));
        assertTrue(trie.remove("me"));
        trie.add("hi");
        trie.add("he");
        assertTrue(trie.remove("he"));
    }

    @Test
    void howManyStartWithPrefix() {
        trie.add("q");
        trie.add("qu");
        trie.add("que");
        trie.add("qur");
        assertEquals(4, trie.howManyStartWithPrefix("q"));
    }

    @Test
    void serialize() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        trie.serialize(outputStream);
        trie.add("hello");
        trie.add("world");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        trie.deserialize(inputStream);
        assertFalse(trie.contains("hello"));
        assertTrue(trie.contains("me"));
    }
}