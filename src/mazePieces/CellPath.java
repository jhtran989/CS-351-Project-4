package mazePieces;

import animationTimer.CellAction;
import animationTimer.CellActionSequence;
import constants.CellType;

import java.util.List;

/**
 * Extends the Cell class that has the visited field and its row/column index
 * with respect to the path grid (containing only "path" cells, as mentioned
 * in Todd's logic in the README)
 */
public class CellPath extends Cell {
    public static final CellPath CELL_PATH_OUT_OF_BOUNDS = new CellPath();

    protected boolean visited;
    protected int pathRowIndex;
    protected int pathColumnIndex;

    public CellPath(double x, double y, double cellSize, CellType cellType,
                    int rowIndex, int columnIndex, int cellID,
                    CellActionSequence cellActionSequence, int pathRowIndex,
                    int pathColumnIndex) {
        super(x, y, cellSize, cellType, rowIndex,
                columnIndex, cellID,
                cellActionSequence);
        visited = false;
        this.pathRowIndex = pathRowIndex;
        this.pathColumnIndex = pathColumnIndex;
    }

    public CellPath() {
    }

    @Override
    public String toString() {
        return "Path cell: r - " + pathRowIndex + " c - " + pathColumnIndex +
                " type - " + cellType;
    }

    public boolean isVisited() {
        return visited;
    }

    public void activateVisited() {
        visited = true;
    }

    public int getPathRowIndex() {
        return pathRowIndex;
    }

    public int getPathColumnIndex() {
        return pathColumnIndex;
    }
}
