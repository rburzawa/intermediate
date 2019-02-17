package pl.sda.intermediate.training.fibonacci;

public class Fibonacci {

    public static Integer calculate (Integer number) {
        if (number == 0 || number == 1) { //number < 2
            return number;
        }
        return calculate(number - 1) + calculate(number - 2);

    }
}

