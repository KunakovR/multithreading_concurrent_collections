package Main;

import static Main.Counter.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static BlockingQueue<String> queueA = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> queueB = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> queueC = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            for (int i = 0; i < 10_000; i++){
                String text = generateText("abc", 100_000);
                try {
                    queueA.put(text);
                    queueB.put(text);
                    queueC.put(text);
                } catch (InterruptedException e) {
                    new RuntimeException(e);
                }
            }
        }).start();

        Thread a = new Thread(() -> {
            char letter = 'a';
            Text result = maxCount(queueA, letter);
            System.out.println("Максимальное значение " + letter + " равно: " + result.getMax());// + " " + result.getText() );
        });
        a.start();

        Thread b = new Thread(() -> {
            char letter = 'b';
            Text result = maxCount(queueB, letter);
            System.out.println("Максимальное значение " + letter + " равно: " + result.getMax());// + " " + result.getText() );
        });
        b.start();

        Thread c = new Thread(() -> {
            char letter = 'c';
            Text result = maxCount(queueC, letter);
            System.out.println("Максимальное значение " + letter + " равно: " + result.getMax());// + " " + result.getText() );
        });
        c.start();

    }

}
