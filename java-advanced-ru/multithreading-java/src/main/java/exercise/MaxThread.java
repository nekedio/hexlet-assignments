package exercise;

// BEGIN
import java.util.Arrays;

public class MaxThread extends Thread {

    private int[] numbers;

    private int maxNumber;

    public int getMaxNumber() {
        return maxNumber;
    }

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        Arrays.sort(numbers);
        Arrays.sort(numbers);
        this.maxNumber = numbers[numbers.length - 1];
    }
}
// END
