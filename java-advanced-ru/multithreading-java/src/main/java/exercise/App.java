package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {

    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numgers) {
        Map<String, Integer> result = new HashMap<>();

        MaxThread maxThread = new MaxThread(numgers);

        MinThread minThread = new MinThread(numgers);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger("Поток \"maxThread\" был прерван");
        }

        try {
            minThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger("Поток \"minThread\" был прерван");
        }

        result.put("max", maxThread.getMaxNumber());
        result.put("min", minThread.getMinNumber());

        return result;
    }
    // END
}
