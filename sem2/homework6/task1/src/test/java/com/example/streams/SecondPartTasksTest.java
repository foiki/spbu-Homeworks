package com.example.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import static com.example.streams.SecondPartTasks.*;
import static org.junit.jupiter.api.Assertions.*;

class SecondPartTasksTest {

    @Test
    void testFindQuotes() {
        List<String> list = Arrays.asList("aaaa", "aaab", "aaac", "aaad", "sad", "sa", "bboring", "bbull", "bbwow");
        assertEquals(Arrays.asList("aaaa", "aaab", "aaac", "aaad"), findQuotes(list, "aa"));
        assertEquals(Arrays.asList("sad", "sa"), findQuotes(list, "s"));
        assertEquals(Arrays.asList("bboring", "bbull", "bbwow"), findQuotes(list, "bb"));
    }

    @Test
    void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 0.001);
    }

    @Test
    void testFindPrinter() {
        List<String> firstAuthor = Arrays.asList("a", "b", "c", "d");
        List<String> secondAuthor = Arrays.asList("a", "b", "c", "de");
        List<String> thirdAuthor = Arrays.asList("a", "b", "cf", "de");
        Map<String, List<String>> authors = new HashMap<>();
        authors.put("firstAuthor", firstAuthor);
        authors.put("secondAuthor", secondAuthor);
        authors.put("thirdAuthor", thirdAuthor);
        assertEquals("thirdAuthor", findPrinter(authors));
    }

    @Test
    void testCalculateGlobalOrder() {
        List<Map<String, Integer>> orders = new ArrayList<>();
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());
        orders.get(0).put("Bananas", 10);
        orders.get(0).put("Apples", 10);
        orders.get(0).put("Chairs", 10);
        orders.get(1).put("Chairs", 42);
        orders.get(1).put("People", 10000);
        orders.get(2).put("Bananas", 42);
        orders.get(2).put("Apples", 42);
        orders.get(3).put("Something new", 1);
        HashMap<String, Integer> answer = new HashMap<>();
        answer.put("Apples", 52);
        answer.put("Bananas", 52);
        answer.put("Chairs", 52);
        answer.put("People", 10000);
        answer.put("Something new", 1);
        assertEquals(answer, SecondPartTasks.calculateGlobalOrder(orders));
    }
}