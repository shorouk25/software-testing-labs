import org.example.Calculator;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void TestMax1() {
        Calculator c = new Calculator();
        int result = c.intMax(4,9,5);
        assertEquals(9, result);
    }

    @Test
    public void TestMax2() {
        Calculator c = new Calculator();
        int result = c.intMax(7,7,7);
        assertEquals(7, result);
    }

    @Test
    public void TestMax3() {
        Calculator c = new Calculator();
        int result = c.intMax(10,0,-8);
        assertEquals(10, result);
    }

}
