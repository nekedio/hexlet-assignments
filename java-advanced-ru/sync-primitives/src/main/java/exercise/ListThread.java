package exercise;

// BEGIN
import java.util.logging.Logger;

class ListThread extends Thread {

    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 1000) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException ex) {
                Logger.getLogger("ERROR");
            }
            list.add(i);

            i++;
        }
    }

}
// END
