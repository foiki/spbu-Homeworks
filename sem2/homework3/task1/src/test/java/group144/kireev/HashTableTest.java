package group144.kireev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void existTest() {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("Hello");
        assertTrue(table.exist("Hello"));
        assertFalse(table.exist("World"));
    }

    @Test
    void addTest() {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.add("bca");
        table.add("acb");
        assertTrue(table.exist("bca"));
        assertTrue(table.exist("abc"));
        assertFalse(table.exist("bba"));
    }

    @Test
    void removeTest() throws ElementDoesNotExist {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.add("bca");
        table.add("acb");
        table.remove("abc");
        assertTrue(table.exist("bca"));
        assertFalse(table.exist("abc"));
    }

    @Test
    void removeExceptionTest() throws ElementDoesNotExist {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.remove("abc");
        assertThrows(ElementDoesNotExist.class, () -> table.remove("abc"));
    }

    @Test
    void changeHashFunctionTest() {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.add("bca");
        table.add("acb");
        table.changeHashFunction(new SumHash());
        assertTrue(table.exist("abc"));
        assertTrue(table.exist("bca"));
        assertTrue(table.exist("acb"));
    }
}