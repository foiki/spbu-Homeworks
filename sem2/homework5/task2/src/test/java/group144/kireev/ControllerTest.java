package group144.kireev;

import org.junit.jupiter.api.Test;

import static group144.kireev.Controller.calculate;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @Test
    void calculateErrorTest() {
        assertEquals("Error!", calculate(1, 0, "/"));
    }

    @Test
    void calculateSimpleTest() {
        assertEquals("10.0", calculate(2, 5, "*"));
        assertEquals("7.0", calculate(3, 4, "+"));
        assertEquals("-5.0", calculate(0, 5, "-"));
        assertEquals("-9.0", calculate(-6, -3, "+"));
        assertEquals("1.0", calculate(-1, -1, "/"));
    }
}