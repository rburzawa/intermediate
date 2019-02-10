package pl.sda.intermediate;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {

    static Integer cash = 1000;
    static Integer counter = 0;
    static AtomicInteger atomicCash = new AtomicInteger(1000);
    static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static /*synchronized*/ void withdraw(Integer howMuch) { //synchronized działa ze zwykłymi Integerami
        cash = cash - howMuch;
        atomicCash.addAndGet(-howMuch);
    }

    public static /*synchronized*/ void deposit(Integer howMuch) {
        cash = cash + howMuch;
        atomicCash.addAndGet(howMuch);
        ++counter;
        atomicCounter.addAndGet(1);
    }


}
