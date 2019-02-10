package pl.sda.intermediate;

public class ClientAction implements Runnable {


    @Override
    public void run() {
        Bank.withdraw(10);
        Bank.deposit(10);



    }
}
