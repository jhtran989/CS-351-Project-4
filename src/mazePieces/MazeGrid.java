package mazePieces;

import animationTimer.CellAction;
import animationTimer.CellActionSequence;
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
    private List<CellAction> cellActionList;
    private CellActionSequence cellActionSequence;

    public MazeGrid(int mazeGridDimension, int cellSize, boolean outline,
                    boolean internalVersion, MazeGrid mazeGridGUI) {
        this.mazeGridDimension = mazeGridDimension;

        if (outline) {
            pathGridDimension = Math.floorDiv(mazeGridDimension, 2);
        } else {
            pathGridDimension = (int) Math.ceil((double) mazeGridDimension / 2);
        }

        mazeGrid = new Cell[mazeGridDimension][mazeGridDimension];
        pathGrid = new CellPath[pathGridDimension][pathGridDimension];
        this.cellSize = cellSize;
        cellCenter = cellSize / 2;
        cellGroup = new Group();

        this.outline = outline;

        cellActionList = new ArrayList<>();
        if (internalVersion) {
            cellActionSequence = new CellActionSequence(mazeGridGUI);
        } else {
            cellActionSequence = new CellActionSequence(this);
        }

        // FIXME
        System.out.println("Outline " + outline);
        System.out.println("Maze dimension " + mazeGridDimension);
        System.out.println("Path dimension " + pathGridDimension);

        initializeMazeBoard();

        printMazeGrid();
        printPathGrid();
    }

    // updated so the LEFT CORNER is used
    private void initializeMazeBoard() {
        int pathRowIndex = 0;
        int pathColumnIndex = 0;
        int cellID = 0;

        for (int i = 0; i < mazeGridDimension; i++) {
            for (int j = 0; j < mazeGridDimension; j++) {
                // should be all WALLs initially
                if (outline) {
                    if (i % 2 == 1 && j % 2 == 1) {
                        mazeGrid[i][j] = new CellPath(j * cellSize,
                                i * cellSize, cellSize,
                                CellType.CELL_WALL, i,
                                j, cellID,
                                cellActionSequence, pathRowIndex,
                                pathColumnIndex);
                        pathGrid[pathRowIndex][pathColumnIndex] =
                                (CellPath) mazeGrid[i][j];
                        pathColumnIndex++;
                    } else {
                        mazeGrid[i][j] = new CellWall(j * cellSize,
                                i * cellSize, cellSize,
                                CellType.CELL_WALL, i,
                                j, cellID, cellActionSequence);
                    }
                } else {
                    if (i % 2 == 0 && j % 2 == 0) {
                        mazeGrid[i][j] = new CellPath(j * cellSize,
                                i * cellSize, cellSize,
                                CellType.CELL_WALL, i,
                                j, cellID,
                                cellActionSequence, pathRowIndex,
                                pathColumnIndex);
                        pathGrid[pathRowIndex][pathColumnIndex] =
                                (CellPath) mazeGrid[i][j];
                        pathColumnIndex++;
                    } else {
                        mazeGrid[i][j] = new CellWall(j * cellSize,
                                i * cellSize, cellSize,
                                CellType.CELL_WALL, i,
                                j, cellID, cellActionSequence);
                    }
                }
//                mazeGrid[i][j] = new Cell(j * cellSize,
//                        i * cellSize, cellSize,
//                        CellType.CELL_WALL, i, j);
                cellGroup.getChildren().add(mazeGrid[i][j]);
                cellID++;
            }

            if (outline) {
                if (i % 2 == 1) {
                    pathRowIndex++;
                    pathColumnIndex = 0;
                }
            } else {
                if (i % 2 == 0) {
                    pathRowIndex++;
                    pathColumnIndex = 0;
                }
            }
        }
    }

    private Cell getNeighborInDirection(Cell cell, Direction direction) {
        CellPath newCell = (CellPath) cell;
        int newRowIndex = newCell.getPathRowIndex()
                + direction.getRowCorrection();
        int newColumnIndex = newCell.getPathColumnIndex()
                + direction.getColumnCorrection();

        if (newRowIndex < 0 || newRowIndex >= pathGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        if (newColumnIndex < 0 || newColumnIndex >= pathGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        return pathGrid[newRowIndex][newColumnIndex];
    }

    public List<Cell> getNeighborList(Cell cell) {
        List<Cell> neighborList = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Cell currentNeighbor = getNeighborInDirection(
                    cell,
                    direction);

            if (currentNeighbor != CELL_OUT_OF_BOUNDS) {
                CellPath currentNeighborPath = (CellPath) currentNeighbor;
                if (!currentNeighborPath.isVisited()) {
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
        System.out.println("First row: " + firstRowIndex);
        System.out.println("Last row: " + lastRowIndex);
        System.out.println("First column: " + firstColumnIndex);
        System.out.println("Last column: " + lastColumnIndex);

        int wallRowIndex;
        int wallColumnIndex;

        if (firstRowIndex == lastRowIndex) {
            wallRowIndex = firstRowIndex;
            wallColumnIndex = firstColumnIndex + 1;
        } else {
            wallRowIndex = firstRowIndex + 1;
            wallColumnIndex = firstColumnIndex;
        }

        // FIXME
        System.out.println("Wall row: " + wallRowIndex);
        System.out.println("Wall column: " + wallColumnIndex);

        mazeGrid[wallRowIndex][wallColumnIndex].setCellType(
                CellType.CELL_WALL_PATH);
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
        System.out.println("First row: " + firstRowIndex);
        System.out.println("Last row: " + lastRowIndex);
        System.out.println("First column: " + firstColumnIndex);
        System.out.println("Last column: " + lastColumnIndex);

        int wallRowIndex;
        int wallColumnIndex;

        if (firstRowIndex == lastRowIndex) {
            wallRowIndex = firstRowIndex;
            wallColumnIndex = firstColumnIndex + 1;
        } else {
            wallRowIndex = firstRowIndex + 1;
            wallColumnIndex = firstColumnIndex;
        }

        // FIXME
        System.out.println("Wall row: " + wallRowIndex);
        System.out.println("Wall column: " + wallColumnIndex);

        // FIXME: will only backtrack IF THE WALL CELL IS ALREADY A PATH
        if (mazeGrid[wallRowIndex][wallColumnIndex].getCellType()
                == CellType.CELL_WALL_PATH) {
            mazeGrid[wallRowIndex][wallColumnIndex].setCellType(
                    CellType.CELL_WALL_BACKTRACK);
        }
    }

    /**
     * Only for debugging purposes...
     */
    public void printMazeGrid() {
        System.out.println();

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
    public void printPathGrid() {
        System.out.println();

        for (Cell[] cells : pathGrid) {
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
        System.out.println();

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

    public int getPathGridDimension() {
        return pathGridDimension;
    }

    public Cell getCellPath(int rowIndex, int columnIndex) {
        return pathGrid[rowIndex][columnIndex];
    }

    public Cell getGeneralCell(int rowIndex, int columnIndex) {
        return mazeGrid[rowIndex][columnIndex];
    }

    public List<CellAction> getCellActionList() {
        return cellActionList;
    }

    public CellActionSequence getCellActionSequence() {
        return cellActionSequence;
    }
}
