package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateErrorTest() {
        assertEquals("Error!", Calculator.calculate(1, 0, "/"));
    }

    @Test
    void calculateSimpleTest() {
        assertEquals("10.0", Calculator.calculate(2, 5, "*"));
        assertEquals("7.0", Calculator.calculate(3, 4, "+"));
        assertEquals("-5.0", Calculator.calculate(0, 5, "-"));
        assertEquals("-9.0", Calculator.calculate(-6, -3, "+"));
        assertEquals("1.0", Calculator.calculate(-1, -1, "/"));
    }
}