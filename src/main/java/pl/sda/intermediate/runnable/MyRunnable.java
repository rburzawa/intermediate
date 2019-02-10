package pl.sda.intermediate.runnable;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread
                .currentThread()
                .getName()
                + " My Runnable");
    }
}
