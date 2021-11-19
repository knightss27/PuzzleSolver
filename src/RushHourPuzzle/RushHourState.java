// Seth Knights
// UNFINISHED
package RushHourPuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;
import TilePuzzle.TilePuzzleAction;
import TilePuzzle.TilePuzzleState;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RushHourState implements State {

    public RushHourBitState state = new RushHourBitState();
    public RushHourBitState horizontalState = new RushHourBitState();
    public RushHourBitState verticalState = new RushHourBitState();

    byte[] vehicles = new byte[16];

    public RushHourState() {
        setVehicle(0, 0, 3, Car.Orientation.HORIZONTAL);
        setVehicle(1, 5, 3, Car.Orientation.VERTICAL);

        for (byte vehicle : vehicles) {
            String orientation = getBit(vehicle, 6) == 0 ? "HORIZONTAL" : "VERTICAL";
            int length = getVehicleLength(vehicle);
            int position = getVehiclePosition(vehicle);

            System.out.println("Vehicle at pos: " + position + ", aligned: " + orientation + ", with length: " + length);
        }
    }

    public RushHourState(String layout) {
        // Layouts work through a string containing the position of each car.
        // For every car, the string is constructed as ORIENTATION|LENGTH - POSITION (0-35)
        // For the X car (special goal car), the orientation must be horizontal, so it uses X-POSITION
        // i.e. "H3-0,V3-5,X2-12"

        String[] vehicleStrings = layout.split(",");
//        System.out.println(Arrays.toString(vehicleStrings));

        for (String vehicle : vehicleStrings) {
            String[] properties = vehicle.split("-");
            int orientation = properties[0].charAt(0) == 'H' || properties[0].charAt(0) == 'X' ? Car.Orientation.HORIZONTAL : Car.Orientation.VERTICAL;
            int length = Integer.parseInt(properties[0].substring(1));
            int position = Integer.parseInt(properties[1]);
//            System.out.println(position);
            if (properties[0].charAt(0) == 'X') {
                setVehicle(0, position, length, orientation);
            } else {
                for (int i = 1; i < vehicles.length; i++) {
                    if (getVehiclePosition(vehicles[i]) == -1) {
                        setVehicle(i, position, length, orientation);
//                        System.out.println("Setting vehicle at " + position + " with length " + length);
                        break;
                    }
                }
            }
        }
//        System.out.println("X car set position: " + getVehiclePosition(vehicles[0]));
    }

    public RushHourState(long state, long verticalState, long horizontalState, byte[] vehicles) {
        this.state = new RushHourBitState(state);
        this.verticalState = new RushHourBitState(verticalState);
        this.horizontalState = new RushHourBitState(horizontalState);
        this.vehicles = vehicles.clone();
    }

    void setVehicle(int id, int position, int length, int orientation) {

        if (id >= 16) {
            System.out.println("INVALID CAR ID");
            return;
        }

        // Add 1 to position so we can easily filter out non-existent cars
        vehicles[id] = (byte) (position+1);
        vehicles[id] = orientation == Car.Orientation.VERTICAL ? setBit(vehicles[id], 6) : vehicles[id];
        vehicles[id] = length == Car.Length.LONG ? setBit(vehicles[id], 7) : vehicles[id];

//        System.out.println(vehicles[0] + " pos " + id);

        if (orientation == Car.Orientation.HORIZONTAL) {
            for (int i = 0; i < length; i++) {
                state.setBit(position + i);
                horizontalState.setBit(position + i);
            }
        } else {
            for (int i = 0; i < length; i++) {
                state.setBit(position + 6 * i);
                verticalState.setBit(position + 6 * i);
            }
        }
    }

    private byte setBit(byte info, int position) {
        return (byte) (info | (1 << position));
    }

    private int getBit(byte info, int position) {
        return ((info >> position) & 1);
    }

    private int getVehiclePosition(byte vehicle) {
        return (vehicle & 63)-1;
    }

    private int getVehicleOrientation(byte vehicle) {
        return getBit(vehicle, 6) == 0 ? Car.Orientation.HORIZONTAL : Car.Orientation.VERTICAL;
    }

    private byte setVehicleLocation(byte info, int location) {
        byte newInfo = (byte) (location+1);
        newInfo = getVehicleOrientation(info) == 1 ? setBit(newInfo, 6) : newInfo;
        newInfo = getVehicleLength(info) == 1 ? setBit(newInfo, 7) : newInfo;
        return newInfo;
    }

    private int getVehicleLength(byte vehicle) {
        return getBit(vehicle, 7) == 0 ? Car.Length.SHORT : Car.Length.LONG;
    }

    public static class RushHourBitState {

        // 6x6 board
        private long state;

        public RushHourBitState() {
            state = 0L;
        }

        public RushHourBitState(long _state) {
            state = _state;
        }

        void setBit(int position) {
            state = state | (1L << position);
        }

        int getBit(int position) {
            return (int) ((state >> position) & 1);
        }

        void clearBit(int position) {
            state = state & (~(1L << position));
        }

        public long getLong() {
            return state;
        }
    }

    @Override
    public List<Action> listActions() {
        LinkedList<Action> actions = new LinkedList<>();
//        System.out.println("Searching for actions");

//        System.out.println(Arrays.toString(vehicles));
        for (byte vehicle : vehicles) {
            int position = getVehiclePosition(vehicle);
            int orientation = getVehicleOrientation(vehicle);
            int length = getVehicleLength(vehicle);

            if (position < 0) {
                continue;
            }

            int negativeShift = 0;
            int positiveShift = 0;

            if (orientation == Car.Orientation.HORIZONTAL) {
                while (position + length + positiveShift < 36 && state.getBit(position + length + positiveShift) != 1 && (position + length + positiveShift) / 6 == position / 6) {
                    positiveShift++;
                }
                if (positiveShift > 0) {
                    actions.add(new RushHourAction(new Car(orientation, length, position), positiveShift));
                }

                while (position - 1 - negativeShift >= 0 && state.getBit(position - 1 - negativeShift) != 1 && (position - 1 - negativeShift) / 6 == position / 6) {
                    negativeShift++;
                }
                if (negativeShift > 0) {
                    actions.add(new RushHourAction(new Car(orientation, length, position), -negativeShift));
                }
            }
            if (orientation == Car.Orientation.VERTICAL) {
                while (position - (6 * (negativeShift+1)) >= 0 && state.getBit(position - (6 * (negativeShift+1))) != 1 && (position - (6 * (negativeShift+1))) % 6 == position % 6) {
                    negativeShift++;
                }
                if (negativeShift > 0) {
                    actions.add(new RushHourAction(new Car(orientation, length, position), -negativeShift));
                }
                while (position + (length + positiveShift) * 6 < 36 && state.getBit(position + (length + positiveShift) * 6) != 1 && (position + (length + positiveShift) * 6) % 6 == position % 6) {
                    positiveShift++;
                }
                if (positiveShift > 0) {
                    actions.add(new RushHourAction(new Car(orientation, length, position), positiveShift));
                }
            }
        }

        return actions;
    }

    @Override
    public boolean isGoalState() {
        int redCarPosition = getVehiclePosition(vehicles[0]);
//        System.out.println(redCarPosition);
//        System.out.println("Returning goal state evaluation: " + (redCarPosition / 6 == 2 && redCarPosition % 6 == 4));
        return redCarPosition / 6 == 2 && redCarPosition % 6 == 4;
    }

    @Override
    public void display() {

        int[][] boardInts = new int[6][6];
        for (int i = 0; i < 16; i++) {
            if (getVehiclePosition(vehicles[i]) >= 0) {
                int position = getVehiclePosition(vehicles[i]);
                int orientation = getVehicleOrientation(vehicles[i]);
                for (int j = 0; j < getVehicleLength(vehicles[i]); j++) {
                    boardInts[(position + (orientation == 0 ? j : j * 6)) / 6][(position + (orientation == 0 ? j : j * 6)) % 6] = i+1;
                }
            }

        }

        StringBuilder boardStr = new StringBuilder("------------------\n");
        for (int i = 0; i < 36; i += 6) {
            StringBuilder line = new StringBuilder("|");
            for (int j = i; j < i+6; j++) {
                line.append(String.format("%3s",state.getBit(j))).append(" ");
            }
            line.append("|\n");
            boardStr.append(line);
        }
        boardStr.append("--------------------------\n");

        String board = Arrays.deepToString(boardInts).replace("], ", "]\n");
        System.out.println(board.substring(1,board.length()-1));
        System.out.println(boardStr);
    }

    @Override
    public State duplicate() {
        return new RushHourState(state.getLong(), verticalState.getLong(), horizontalState.getLong(), vehicles);
    }

    @Override
    public void performAction(Action action) {
        RushHourAction newAction = (RushHourAction) action;

        if (newAction.car.position < 0 || newAction.car.position >= 36 || state.getBit(newAction.car.position) != 1) {
            System.out.println("INVALID ACTION");
            newAction.display();
            display();
            List<Action> actions = listActions();
            for (Action action1 : actions) {
                action1.display();
            }

            int vehicleID = 0;
            for (int i = 0; i < vehicles.length; i++) {
                if (getVehiclePosition(vehicles[i]) == newAction.car.position) {
                    vehicleID = i;
                }
            }

            System.out.println(getVehiclePosition(vehicles[vehicleID]));

            throw new RuntimeException("Bad action");
//            return;
        }

        int vehicleID = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if (getVehiclePosition(vehicles[i]) == newAction.car.position) {
                vehicleID = i;
            }
        }

        if (newAction.car.orientation == Car.Orientation.HORIZONTAL) {
            int shiftDir = newAction.shift / Math.abs(newAction.shift);
            if ((newAction.car.position + newAction.shift) / 6 >= 0 && (newAction.car.position + newAction.shift + newAction.car.length-1) % 6 > 0 && state.getBit(newAction.car.position + newAction.shift + (shiftDir > 0 ? newAction.car.length-1 : 0)) != 1) {
                for (int i = 1; i < Math.abs(newAction.shift)+1; i++) {
                    int bitToAdd = newAction.car.position + (i * shiftDir) + (shiftDir > 0 ? newAction.car.length-1 : 0);
                    int bitToRemove = bitToAdd - shiftDir * newAction.car.length;

                    state.setBit(bitToAdd);
                    state.clearBit(bitToRemove);

                    horizontalState.setBit(bitToAdd);
                    horizontalState.clearBit(bitToRemove);

                    vehicles[vehicleID] = setVehicleLocation(vehicles[vehicleID], newAction.car.position + newAction.shift);
                }
            }
        }
        if (newAction.car.orientation == Car.Orientation.VERTICAL) {
            if ((newAction.car.position + newAction.shift * 6) / 6 >= 0 && (newAction.car.position + newAction.shift * 6 + (newAction.car.length-1) * 6) / 6 <= 5 && state.getBit(newAction.car.position + newAction.shift * 6 + (newAction.car.length-1) * 6) != 1) {

                int shiftDir = newAction.shift / Math.abs(newAction.shift);
                for (int i = 0; i < Math.abs(newAction.shift); i++) {
                    int bitToAdd = newAction.car.position + (i * shiftDir * 6) + (shiftDir > 0 ? newAction.car.length * 6 : 0);
                    int bitToRemove = bitToAdd - newAction.car.length * 6;

                    state.setBit(bitToAdd);
                    state.clearBit(bitToRemove);

                    verticalState.setBit(bitToAdd);
                    verticalState.clearBit(bitToRemove);

                    vehicles[vehicleID] = setVehicleLocation(vehicles[vehicleID], newAction.car.position + newAction.shift * 6);
                }
            }
        }
    }

    @Override
    public int heuristic() {
        return 0;
    }
}
