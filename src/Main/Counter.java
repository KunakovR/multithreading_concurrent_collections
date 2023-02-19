package Main;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Counter {

    public static Text maxCount(BlockingQueue<String> queue, char letter) {
        int count = 0;
        int max = 0;
        String text;
        String example = null;
        Text set = null;
        try {
            for (int i = 0; i < 10_000; i++) {
                text = queue.take();
                for (char abc : text.toCharArray()) {
                    if (abc == letter) count++;
                }
                if (count > max) {
                    max = count;
                    example = text;
                }
                count = 0;
            }
            set = new Text(max, example);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return set;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < length; j++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}
