package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.training.runnable.MyRunnable;

public class ThreadsExample {
    @Test
    void runnableBasics() { //praca do wykonania
        Runnable runnable = () -> System.out.println(Thread
                .currentThread()
                .getName()
                + " Lambda Runnable");

        Runnable runnableAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread
                        .currentThread()
                        .getName()
                        + " Anonumous Runnable");
            }
        };

        Runnable myRunnable = new MyRunnable();

        // praca uruchamia się w pojedynczych wątkach
        runnable.run();
        runnableAnonymous.run();
        myRunnable.run();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnableAnonymous);
        Thread thread3 = new Thread(myRunnable);

        // żeby praca uruchomiła się w jednym nowym wątku robimy 'start'
        thread1.start();
        thread2.start();
        thread3.start();


    }
}
