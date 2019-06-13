package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateErrorTest() {
        assertThrows(IncorrectExpression.class, () -> Calculator.calculate(1, 0, "/"));
    }

    @Test
    void calculateSimpleTest() throws IncorrectExpression {
        assertEquals(10, Calculator.calculate(2, 5, "*"));
        assertEquals(7, Calculator.calculate(3, 4, "+"));
        assertEquals(-5, Calculator.calculate(0, 5, "-"));
        assertEquals(-9, Calculator.calculate(-6, -3, "+"));
        assertEquals(1, Calculator.calculate(-1, -1, "/"));
    }
}