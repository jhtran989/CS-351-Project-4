package mazePieces;

import constants.Direction;
import static mazePieces.Cell.CELL_OUT_OF_BOUNDS;

public class MazeBoard {
    private int dimension;
    private Cell[][] cellArray;

    public MazeBoard(int dimension) {
        this.dimension = dimension;

        cellArray = new Cell[dimension][dimension];
    }

    private void initializeMazeBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // should be all WALLs initially
//                cellArray[i][j] = new Cell();
            }
        }
    }

    private Cell getCellInDirection(Cell cell, Direction direction) {
        int newRowIndex = cell.getRowIndex() + direction.getRowCorrection();
        int newColumnIndex = cell.getColumnIndex()
                + direction.getColumnCorrection();

        if (newRowIndex < 0 || newRowIndex > dimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        if (newColumnIndex < 0 || newColumnIndex > dimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        return cellArray[newRowIndex][newColumnIndex];
    }
}
