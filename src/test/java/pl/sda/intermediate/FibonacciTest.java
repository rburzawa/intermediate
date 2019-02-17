package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate.training.fibonacci.Fibonacci;

public class FibonacciTest {

@Test
    void fibonacciTest(){

    Assertions.assertEquals(Integer.valueOf(13), Fibonacci.calculate(7));
    Assertions.assertEquals(Integer.valueOf(5), Fibonacci.calculate(5));
    Assertions.assertEquals(Integer.valueOf(21), Fibonacci.calculate(8));
}
}
