package pl.sda.intermediate;

import java.util.Random;

public class ClientAction implements Runnable {


    @Override
    public void run() {
        Integer howMuch = new Random().nextInt(101);


        Bank.withdraw(howMuch);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(howMuch);



    }
}
