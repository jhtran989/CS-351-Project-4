package constants;

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
}
