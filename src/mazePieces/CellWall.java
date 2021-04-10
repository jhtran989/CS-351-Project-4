package mazePieces;

import constants.CellType;

public class CellWall extends Cell {
    protected boolean takenDown;

    public CellWall(double x, double y, double cellSize, CellType cellType, int rowIndex, int columnIndex) {
        super(x, y, cellSize, cellType, rowIndex, columnIndex);
        takenDown = false;
    }
}
