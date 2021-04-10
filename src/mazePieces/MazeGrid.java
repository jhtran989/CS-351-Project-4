package mazePieces;

import constants.CellType;
import constants.Direction;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

import static mazePieces.Cell.CELL_OUT_OF_BOUNDS;

public class MazeGrid {
    private final int mazeGridDimension;
    private final int pathGridDimension;
    private final int cellSize;
    private final int cellCenter; // used to center the cells
    private Cell[][] mazeGrid;
    private CellPath[][] pathGrid;
    private Group cellGroup;
    private boolean outline;

    public MazeGrid(int mazeGridDimension, int cellSize, boolean outline) {
        this.mazeGridDimension = mazeGridDimension;

        mazeGrid = new Cell[mazeGridDimension][mazeGridDimension];
        this.cellSize = cellSize;
        cellCenter = cellSize / 2;
        cellGroup = new Group();

        this.outline = outline;

        if (outline) {
            pathGridDimension = Math.floorDiv(mazeGridDimension, 2);
        } else {
            pathGridDimension = (int) Math.ceil((double) mazeGridDimension / 2);
        }

        initializeMazeBoard();
    }

    // updated so the LEFT CORDER is used
    private void initializeMazeBoard() {
        for (int i = 0; i < mazeGridDimension; i++) {
            for (int j = 0; j < mazeGridDimension; j++) {
                // should be all WALLs initially
                mazeGrid[i][j] = new Cell(j * cellSize,
                        i * cellSize, cellSize,
                        CellType.CELL_WALL, i, j);
                cellGroup.getChildren().add(mazeGrid[i][j]);
            }
        }
    }

    public CellPath getCellPath(int rowIndex, int columnIndex) {
        return pathGrid[rowIndex][columnIndex];
    }

    private Cell getCellInDirection(Cell cell, Direction direction) {
        int newRowIndex = cell.getRowIndex() + direction.getRowCorrection();
        int newColumnIndex = cell.getColumnIndex()
                + direction.getColumnCorrection();

        if (newRowIndex < 0 || newRowIndex >= mazeGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        if (newColumnIndex < 0 || newColumnIndex >= mazeGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        return mazeGrid[newRowIndex][newColumnIndex];
    }

    private Cell getNeighborInDirection(Cell cell, Direction direction) {
        Cell neighborCell = cell;

        for (int i = 0; i < 2; i++) {
            neighborCell = getCellInDirection(neighborCell,
                    direction);

            if (neighborCell == CELL_OUT_OF_BOUNDS) {
                break;
            }
        }

        return neighborCell;
    }

    public List<Cell> getNeighborList(Cell cell) {
        List<Cell> neighborList = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Cell currentNeighbor = getNeighborInDirection(cell,
                    direction);

            if (currentNeighbor != CELL_OUT_OF_BOUNDS) {
                if (!currentNeighbor.isVisited()) {
                    neighborList.add(currentNeighbor);
                }
            }
        }

        return neighborList;
    }

    public void connectCells(Cell currentCell, Cell neighborCell) {
        int firstRowIndex = Math.min(currentCell.getRowIndex(),
                neighborCell.getRowIndex());
        int lastRowIndex = Math.max(currentCell.getRowIndex(),
                neighborCell.getRowIndex());
        int firstColumnIndex = Math.min(currentCell.getColumnIndex(),
                neighborCell.getColumnIndex());
        int lastColumnIndex = Math.max(currentCell.getColumnIndex(),
                neighborCell.getColumnIndex());

        // FIXME
        System.out.println("First row: " + firstColumnIndex);
        System.out.println("Last row: " + lastRowIndex);
        System.out.println("First column: " + firstColumnIndex);
        System.out.println("Last column: " + lastColumnIndex);

        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            for (int j = firstColumnIndex;
                 j <= lastColumnIndex; j++) {
                // FIXME
                System.out.println("Current i: " + i);
                System.out.println("Current j: " + j);

                if (!(i == currentCell.getRowIndex()
                        && j == currentCell.getColumnIndex())) {
                    mazeGrid[i][j].setCellType(CellType.CELL_PATH);

                    // TODO: maybe keep track of a list of cells a cell is
                    // connected to?
                }
            }
        }
    }

    public void backtrackCells(Cell currentCell, Cell backtrackCell) {
        int firstRowIndex = Math.min(currentCell.getRowIndex(),
                backtrackCell.getRowIndex());
        int lastRowIndex = Math.max(currentCell.getRowIndex(),
                backtrackCell.getRowIndex());
        int firstColumnIndex = Math.min(currentCell.getColumnIndex(),
                backtrackCell.getColumnIndex());
        int lastColumnIndex = Math.max(currentCell.getColumnIndex(),
                backtrackCell.getColumnIndex());

        // FIXME
        System.out.println("First row: " + firstColumnIndex);
        System.out.println("Last row: " + lastRowIndex);
        System.out.println("First column: " + firstColumnIndex);
        System.out.println("Last column: " + lastColumnIndex);

        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            for (int j = firstColumnIndex;
                 j <= lastColumnIndex; j++) {
                // FIXME
                System.out.println("Current i: " + i);
                System.out.println("Current j: " + j);

                if (!(i == backtrackCell.getRowIndex()
                        && j == backtrackCell.getColumnIndex())) {
                    mazeGrid[i][j].setCellType(CellType.CELL_PATH_BACKTRACK);

                    // TODO: maybe keep track of a list of cells a cell is
                    // connected to?
                }
            }
        }
    }

    /**
     * Only for debugging purposes...
     */
    public void printMazeGrid() {
        for (Cell[] cells : mazeGrid) {
            for (Cell cell : cells) {
                System.out.print(cell.getCellType() + " ");
            }

            System.out.println();
        }
    }

    /**
     * Only for debugging purposes...
     */
    public void printMazeGridAddresses() {
        for (Cell[] cells : mazeGrid) {
            for (Cell cell : cells) {
                System.out.print(cell + " ");
            }

            System.out.println();
        }
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

    public Cell[][] getMazeGrid() {
        return mazeGrid;
    }

    public int getMazeGridDimension() {
        return mazeGridDimension;
    }

    public void updateGroup(Cell cell) {
        cellGroup.getChildren().add(cell);
    }
}
