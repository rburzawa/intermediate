package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BankTest {

    @Test
    void synchronizedBankTest() {
        for (int i=0; i<100; i++){
          ClientAction clientAction = new ClientAction();
          clientAction.run();
        }
        System.out.println(Bank.counter);
        System.out.println(Bank.cash);
    }

    @Test
    void threadBankTest() {

        List<Thread> threadList = new ArrayList<>(); //lista pracowników

        for (int i = 0; i < 100; i++) { // w pętli tworzymy 100 pracowników
            ClientAction clientAction = new ClientAction(); //każdemu nowemu pracownikowi...
            threadList.add(new Thread(clientAction)); // przydzeilamy nowy task do wykonania
        }

        for (Thread thread : threadList) {
          thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Bank.counter);
        System.out.println(Bank.cash);
        System.out.println();
        System.out.println(Bank.atomicCounter);
        System.out.println(Bank.atomicCash);


    }
}
