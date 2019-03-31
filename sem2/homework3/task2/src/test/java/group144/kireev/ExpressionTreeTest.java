package group144.kireev;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    @Test
    void normalExpressionTest() throws WrongExpressionException {
        ExpressionTree tree = new ExpressionTree();
        tree.add(new Scanner("* + 5 7 - 6 3"));
        assertEquals(36, tree.calculate());
    }

    @Test
    void incorrectExpressionTest() {
        ExpressionTree tree = new ExpressionTree();
        try {
            tree.add(new Scanner("* + 5 7"));
            tree.calculate();
        } catch (WrongExpressionException e) {
            assertEquals("Wrong expression!", e.getMessage());
        }
    }

    @Test
    void expressionOnlyWithNumbersTest() throws WrongExpressionException {
        ExpressionTree tree = new ExpressionTree();
        tree.add(new Scanner("15"));
        assertEquals(15, tree.calculate());
    }

    @Test
    void incorrectExpressionWithLessSignsTest() {
        ExpressionTree tree = new ExpressionTree();
        try {
            tree.add(new Scanner("* + 5 7 9 10"));
            tree.calculate();
        } catch (WrongExpressionException e) {
            assertEquals("Wrong expression!", e.getMessage());
        }
    }
}