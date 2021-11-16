// Seth Knights
// UNFINISHED
package RushHourPuzzle;

public class Car {

    public int orientation;
    public int length;
    public int position;

    public Car(int orientation, int length, int position) {
        this.orientation = orientation;
        this.length = length;
        this.position = position;
    }

    public static final class Orientation {
        public static final int HORIZONTAL = 0;
        public static final int VERTICAL = 1;
    }

    public static final class Length {
        public static final int SHORT = 2;
        public static final int LONG = 3;
    }
}
