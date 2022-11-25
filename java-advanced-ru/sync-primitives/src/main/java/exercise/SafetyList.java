package exercise;

import java.util.Arrays;

class SafetyList {

    // BEGIN
    private int[] data = new int[0];
    private int length = 0;

    public synchronized void add(int value) {
        int[] newData = new int[length + 1];

        int i = 0;
        while (i < length) {
            newData[i] = data[i];
            i++;
        }
        newData[length] = value;

        data = newData;
        length++;
    }

    public int get(int index) {
        return data[index];
    }

    public int getSize() {
        return length;
    }

    @Override
    public String toString() {
        String res = Arrays.toString(data);
        return res;
    }
    // END
}
