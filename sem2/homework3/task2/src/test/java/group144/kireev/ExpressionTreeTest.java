package group144.kireev;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTreeTest {
    @Test
    public void normalExpressionTest() throws WrongExpressionException {
        ExpressionTree tree = new ExpressionTree();
        tree.add(new Scanner("* + 5 7 - 6 3"));
        assertEquals(36, tree.calculate());
    }

    @Test
    public void incorrectExpressionTest() {
        ExpressionTree tree = new ExpressionTree();
        try {
            tree.add(new Scanner("* + 5 7"));
            tree.calculate();
        } catch (WrongExpressionException e) {
            assertEquals("Wrong expression!", e.getMessage());
        }
    }

    @Test
    public void expressionOnlyWithNumbersTest() throws WrongExpressionException {
        ExpressionTree tree = new ExpressionTree();
        tree.add(new Scanner("15"));
        assertEquals(15, tree.calculate());
    }

    @Test
    public void incorrectExpressionWithLessSignsTest() {
        ExpressionTree tree = new ExpressionTree();
        try {
            tree.add(new Scanner("* + 5 7 9 10"));
            tree.calculate();
        } catch (WrongExpressionException e) {
            assertEquals("Wrong expression!", e.getMessage());
        }
    }
}