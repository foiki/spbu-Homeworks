package group144.kireev;

import org.junit.jupiter.api.Test;
import static group144.kireev.Calculator.calculate;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void normalExpressionTest() throws WrongExpressionException {
        String expression = "3 2 5 6 - * + 4 *";
        assertEquals(4, calculate(expression));
    }

    @Test
    void wrongExpressionTest() {
        String expression = "3 2 - * + 4 *";
        assertThrows(WrongExpressionException.class, () -> calculate(expression));
    }

    @Test
    void bigNumbersTest() throws WrongExpressionException {
        String expression = "100 30 * 15 6 - 12 * 3 / + -100 * 5 /";
        assertEquals(-60720, calculate(expression));
    }
}