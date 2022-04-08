package processor;

/**
 * Basic utility class for handling custom math functions.
 */
public class Utility {
    /**
     * Forces a value to remain within the bounds of 2 other valuess (low and high)
     * @param val the base value to use
     * @param low the lowest value can be
     * @param high the biggest value can be
     * @return the value clamped within the given range
     */
    public static int clamp(int val, int low, int high) {
        if(val < low) return low;
        if(val > high) return high;
        return val;
    }
}
