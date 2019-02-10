package pl.sda.intermediate;

public class Bank {

    static Integer cash = 1000;
    static Integer counter = 0;

    public static void withdraw(Integer howMuch) {
        cash = cash - howMuch;
    }

    public static void deposit(Integer howMuch) {
        cash = cash + howMuch;
        ++counter;
    }


}
