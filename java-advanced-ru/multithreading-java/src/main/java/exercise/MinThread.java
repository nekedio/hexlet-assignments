package exercise;

// BEGIN
import java.util.Arrays;

public class MinThread extends Thread {

    private int[] numbers;

    private int minNumber;

    public int getMinNumber() {
        return minNumber;
    }

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        Arrays.sort(numbers);
        this.minNumber = numbers[0];
    }
}
// END
