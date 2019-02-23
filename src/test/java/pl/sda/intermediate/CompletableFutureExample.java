package pl.sda.intermediate;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExample {

    Function<BigDecimal, String> bigDecimalToString = s -> s.toString();
    Function<Long, String> longToString = s -> s.toString();
    Function<String, String> stringToString = s -> s;

    @Test
    public void oneByOne() {
        transform(downloadDescription(), stringToString);
        transform(downloadPrice(), bigDecimalToString);
        transform(downloadPhotos(), stringToString);
        transform(downloadData(), longToString);
    }

    @Test
    void threads() {
        Thread t1 = new Thread(() -> transform(downloadDescription(), stringToString));
        Thread t2 = new Thread(() -> transform(downloadPrice(), bigDecimalToString));
        Thread t3 = new Thread(() -> transform(downloadPhotos(), stringToString));
        Thread t4 = new Thread(() -> transform(downloadData(), longToString));

        List<Thread> threadList = Stream.of(t1, t2, t3, t4).collect(Collectors.toList());//collect(Collectors.toCollection(LinkedList::new
        for (Thread thread : threadList) {
            thread.start(); //uzywamy start, aby uruchomić operację w nowych wątkach, a nie w main (tak zadziała run).
        }

        for (Thread thread : threadList) {
            try {
                thread.join();//czekamy na wykonanie każdego wątku po kolei
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
        //użycie puli wątków
    void completableFutures() {

        CompletableFuture<String> descrCF = CompletableFuture
                .supplyAsync(() -> downloadDescription()) //coś zostanie kiedyś ściągnięte
                .thenApplyAsync(s -> transform(s, stringToString)); //wykonaj coś na tym, co zostanie kiedyś sciągnięte

        CompletableFuture<String> photosCF = CompletableFuture
                .supplyAsync(() -> downloadPhotos())
                .thenApplyAsync(s -> transform(s, stringToString));

        CompletableFuture<String> priceCF = CompletableFuture
                .supplyAsync(() -> downloadPrice()) //coś zostanie kiedyś ściągnięte
                .thenApplyAsync(s -> transform(s, bigDecimalToString));

        CompletableFuture<String> dataCF = CompletableFuture
                .supplyAsync(() -> downloadData()) //coś zostanie kiedyś ściągnięte
                .thenApplyAsync(s -> transform(s, longToString));

        List<CompletableFuture<String>> cFS = Stream.of(descrCF, photosCF, priceCF, dataCF).collect(Collectors.toList());

        for (CompletableFuture<String> cF : cFS) {
            cF.join(); //czekamy na wykonanie każdego z zadań
        }

    }

    private <T> String transform(T value, Function<T, String> transformer) {
        simulateDelay(2000);
        return transformer.apply(value);
    }

    private String downloadDescription() {
        simulateDelay(2000);
        return "opis";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        return BigDecimal.valueOf(125.2);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        return "zdjecia";
    }

    private Long downloadData() {
        simulateDelay(3300);
        return 12L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ToString
    @AllArgsConstructor
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalData;
    }


}
