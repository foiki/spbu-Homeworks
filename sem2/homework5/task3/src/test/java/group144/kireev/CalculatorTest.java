package group144.kireev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateTest() {
        assertEquals(765, Calculator.calculate("190 * 4 + 5"));
        assertEquals(21, Calculator.calculate("5 + 3 * 6 - 2 / 1"));
        assertEquals(70, Calculator.calculate("90 / 3 + 10 + 5 * 6"));
        assertEquals(115, Calculator.calculate("5 * 3 + 10 * 500 / 50"));
        assertEquals(50987, Calculator.calculate("7283 * 7 - 2 + 4 * 2"));
    }
}