package constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the direction of the maze grid with the corresponding
 * row/column corrections and has other methods to get the
 * clockwise/counterclockwise list of directions with a given starting
 * direction or finding the direction in a clockwise manner
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int rowCorrection;
    private final int columnCorrection;

    Direction(int rowCorrection, int columnCorrection) {
        this.rowCorrection = rowCorrection;
        this.columnCorrection = columnCorrection;
    }

    public int getRowCorrection() {
        return rowCorrection;
    }

    public int getColumnCorrection() {
        return columnCorrection;
    }

    public static List<Direction> getClockwiseDirectionList(
            Direction startingDirection) {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(UP);
        directionList.add(RIGHT);
        directionList.add(DOWN);
        directionList.add(LEFT);

        while (startingDirection != directionList.get(0)) {
            leftShiftList(directionList);
        }

        return directionList;
    }

    public static List<Direction> getCounterClockwiseDirectionList(
            Direction startingDirection) {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(UP);
        directionList.add(LEFT);
        directionList.add(DOWN);
        directionList.add(RIGHT);

        while (startingDirection != directionList.get(0)) {
            leftShiftList(directionList);
        }

        return directionList;
    }

    private static void leftShiftList(List<Direction> directionList) {
        Direction leftMostDirection = directionList.get(0);
        directionList.remove(0);
        directionList.add(leftMostDirection);
    }

    private void rightShiftList(List<Direction> directionList) {

    }

    public static Direction getClockwiseDirection(Direction direction) {
        switch (direction) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
