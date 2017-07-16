package mobiskif.healthy;

import android.util.Log;

public class L {

    static void d(int i) {
        d(String.valueOf(i));
    }
    static void d(int i, Object o) {
        d(o.getClass() + " -> " + String.valueOf(i));
    }

    static void d(String s) {
        int debug = 1;
        switch (debug) {
            case 1:
                Log.d("jop", s);
                break;
            default:
                break;
        }
    }

    public static void d(String s, Object o) {
        d(o.getClass() + " -> " + s);
    }
}
