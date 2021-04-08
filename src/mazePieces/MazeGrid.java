package mazePieces;

import constants.Direction;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

import static mazePieces.Cell.CELL_OUT_OF_BOUNDS;

public class MazeGrid {
    private final int dimension;
    private final int cellSize;
    private final int cellCenter; // used to center the cells
    private Cell[][] cellGrid;
    private Group cellGroup;

    public MazeGrid(int dimension, int cellSize) {
        this.dimension = dimension;

        cellGrid = new Cell[dimension][dimension];
        this.cellSize = cellSize;
        cellCenter = cellSize / 2;
        cellGroup = new Group();
    }

    private void initializeMazeBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // should be all WALLs initially
                cellGrid[i][j] = new Cell(cellCenter + j * cellSize,
                        cellCenter + i * cellSize, cellSize,
                        CellType.WALL);
                cellGroup.getChildren().add(cellGrid[i][j]);
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

        return cellGrid[newRowIndex][newColumnIndex];
    }

    private Cell getNeighborInDirection(Cell cell, Direction direction) {
        Cell neighborCell = cell;

        for (int i = 0; i < 2; i++) {
            neighborCell = getCellInDirection(neighborCell,
                    direction);
        }

        return neighborCell;
    }

    public List<Cell> getNeighborList(Cell cell) {
        List<Cell> neighborList = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Cell currentNeighbor = getNeighborInDirection(cell,
                    direction);

            if (currentNeighbor != CELL_OUT_OF_BOUNDS) {
                neighborList.add(currentNeighbor);
            }
        }

        return neighborList;
    }

    /***
     * //TODO
     * GETTERS AND SETTERS FOR ALL OF OUR VALUES.
     * (will probably not be final, we will trim to the ones we use)
     * @return returns the sought after values for every get or set.
     */
    public Group getCellGroup() {
        return cellGroup;
    }

    public Cell[][] getCellGrid() {
        return cellGrid;
    }
}
