// Seth Knights
public class BitUtils {
    static int getBit(long bits, int position) {
        return (int) ((bits >> position) & 1);
    }

    static long setBit(long bits, int position) {
        return bits | (1 << position);
    }

    static long clearBit(long bits, int position) {
        return bits & (~(1 << position));
    }

    static String getPaddedBitString(long bits) {
        return String.format("%64s", Long.toBinaryString(bits)).replace(" ", "0");
    }
}