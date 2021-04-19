package mazePieces;

import animationTimer.CellAction;
import animationTimer.CellActionSequence;
import constants.CellType;

import java.util.List;

/**
 * Extends the Cell class and is basically a marker for the "wall" cells
 */
public class CellWall extends Cell {
    protected boolean takenDown;

    public CellWall(double x, double y, double cellSize, CellType cellType,
                    int rowIndex, int columnIndex, int cellID,
                    CellActionSequence cellActionSequence) {
        super(x, y, cellSize, cellType, rowIndex,
                columnIndex, cellID,
                cellActionSequence);
        takenDown = false;
    }
}
