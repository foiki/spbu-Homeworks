package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void normalExpressionTest() {
        String expression = "3 2 5 6 - * + 4 *";
        try {
            assertEquals(4, (new Calculator()).calculate(expression));
        } catch (WrongExpressionException e) {}
    }

    @Test
    public void wrongExpressionTest() {
        String expression = "3 2 - * + 4 *";
        try {
            assertEquals(4, (new Calculator()).calculate(expression));
        } catch (WrongExpressionException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void bigNumbersTest() {
        String expression = "100 30 * 15 6 - 12 * 3 / + -100 * 5 /";
        try {
            assertEquals(-60720, (new Calculator()).calculate(expression));
        } catch (WrongExpressionException e) {}
    }
}