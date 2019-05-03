package group144.kireev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static group144.kireev.HashTable.changeHashFunction;

class HashTableTest {

    @Test
    void addTest() {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.add("bca");
        table.add("acb");
        assertEquals(3, table.getNumberOfAddedWords());
    }

    @Test
    void existTest() {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("Hello");
        assertTrue(table.exist("Hello"));
        assertFalse(table.exist("World"));
    }

    @Test
    void removeTest() throws ElementDoesNotExist {
        HashTable table = new HashTable(new PolynomialHash());
        table.add("abc");
        table.add("bca");
        table.add("acb");
        table.remove("abc");
        assertEquals(2, table.getNumberOfAddedWords());
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
        changeHashFunction(table, new SumHash());
        assertTrue(table.exist("abc"));
        assertTrue(table.exist("bca"));
        assertTrue(table.exist("acb"));
    }
}