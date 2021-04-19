package mazePieces;

import animationTimer.CellAction;
import animationTimer.CellActionSequence;
import constants.CellType;
import constants.Direction;
import javafx.scene.Group;
import mazeGenerator.MazeGenerator;

import java.util.ArrayList;
import java.util.List;

import static mazePieces.Cell.CELL_OUT_OF_BOUNDS;

/**
 * A crucial layer of abstraction that has all the major checking on the maze
 * grid (like finding the neighbors, connecting two "path" cells, updating
 * the backtracking from DFS, etc.) and includes a nice feature: having an
 * outline or not of "wall" cells (just for visualization purposes)
 */
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
                        cellID++;
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
                        cellID++;
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

//    public void initializeSolverPointsGUI(MazeGenerator mazeGenerator) {
//        Cell firstS
//    }

    public List<Cell> getEdgesList() {
        List<Cell> edgesList = new ArrayList<>();

        int startIndex;
        if (outline) {
            startIndex = 1;

            for (int i = 0; i < pathGridDimension; i++) {
                if (i % 2 == 1) {
                    for (int j = startIndex + 1; j < mazeGridDimension; j+= 2) {
                        edgesList.add(mazeGrid[startIndex + 2 * i][j]);
                    }
                } else {
                    for (int j = startIndex; j < mazeGridDimension; j+= 2) {
                        edgesList.add(mazeGrid[startIndex + 2 * i][j]);
                    }
                }
            }
        } else {
            startIndex = 0;

            for (int i = 0; i < pathGridDimension; i++) {
                if (i % 2 == 0) {
                    for (int j = startIndex + 1; j < mazeGridDimension; j+= 2) {
                        edgesList.add(mazeGrid[startIndex + 2 * i][j]);
                    }
                } else {
                    for (int j = startIndex; j < mazeGridDimension; j+= 2) {
                        edgesList.add(mazeGrid[startIndex + 2 * i][j]);
                    }
                }
            }
        }

        //FIXME:
        System.out.println();
        System.out.println("Edges list:");
        System.out.println(edgesList);

        return edgesList;
    }

    public ArrayList<Edge> getAllEdges(){
        ArrayList<Edge> edges = new ArrayList<>();

        int startIndex;
        if (outline) {
            startIndex = 1;
        } else {
            startIndex = 0;
        }

        for (int i = 0; i < (pathGridDimension); i++) {
            for (int j = 0; j < pathGridDimension; j++) {
                if (j < pathGridDimension - 1){ //add to right
                    Cell cellWall = mazeGrid[startIndex + 2 * i]
                            [startIndex + 2 * j + 1];

                    edges.add(new Edge(pathGrid[i][j],
                            pathGrid[i][j + 1],
                            true, false, cellWall));
                }
                if (i < pathGridDimension - 1){
                    Cell cellWall = mazeGrid[startIndex + 2 * i + 1]
                            [startIndex + 2 * j];

                    edges.add(new Edge(pathGrid[i][j],
                            pathGrid[i + 1][j],
                            false, true, cellWall));
                }
            }
        }

        //FIXME:
        for (Edge edge : edges) {
            System.out.println(edge.getCellWall());
        }
        System.out.println("Edges size: " + edges.size());

        return edges;
    }

//    public List<Cell> getBucketCells() {
////        List<Cell>
////
////        for (Cell[] cells : mazeGrid) {
////            for (Cell cell : cells) {
////
////            }
////        }
//    }

    public Cell getRightWall(Cell cell, Direction direction) {
        Direction wallDirection = Direction.getClockwiseDirection(
                direction);

        return getWallInDirection(cell, wallDirection);
    }

    private Cell getWallInDirection(Cell cell, Direction direction) {
        CellPath newCell = (CellPath) cell;
        int newRowIndex = newCell.getRowIndex()
                + direction.getRowCorrection();
        int newColumnIndex = newCell.getColumnIndex()
                + direction.getColumnCorrection();

        if (newRowIndex < 0 || newRowIndex >= mazeGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        if (newColumnIndex < 0 || newColumnIndex >= mazeGridDimension) {
            return CELL_OUT_OF_BOUNDS;
        }

        return mazeGrid[newRowIndex][newColumnIndex];
    }

    public Cell getNeighborInDirection(Cell cell, Direction direction) {
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

    public List<Cell> getAllNeighborList(Cell cell) {
        List<Cell> neighborList = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Cell currentNeighbor = getNeighborInDirection(
                    cell,
                    direction);

            if (currentNeighbor != CELL_OUT_OF_BOUNDS) {
                neighborList.add(currentNeighbor);
            }
        }

        return neighborList;
    }

    public ArrayList<Cell> getNeighborsBreakWalls(Cell c){
        ArrayList<Cell> neighbors = new ArrayList<>();

        CellPath cTemp = (CellPath) c;
        int cRowIndex = cTemp.getPathRowIndex();
        int cColumnIndex = cTemp.getPathColumnIndex();

        if (cRowIndex > 0){ //check up
            if (!pathGrid[cRowIndex - 1][cColumnIndex].isVisited()){
                neighbors.add(pathGrid[cRowIndex - 1][cColumnIndex]);
                c.setUpWall(false);
                pathGrid[cRowIndex - 1][cColumnIndex].setDownWall(false);
                //addCellIDToActions(c);
                //addCellIDToActions(BOARD[c.getROW() - 1][c.getCOL()]);
            }
        }
        if (cRowIndex < (pathGridDimension - 1)){ //check down
            if (!pathGrid[cRowIndex + 1][cColumnIndex].isVisited()){
                neighbors.add(pathGrid[cRowIndex + 1][cColumnIndex]);
                c.setDownWall(false);
                pathGrid[cRowIndex + 1][cColumnIndex].setUpWall(false);
                //addCellIDToActions(c);
                //addCellIDToActions(BOARD[c.getROW() + 1][c.getCOL()]);
            }
        }
        if (cColumnIndex > 0){ //check left
            if (!pathGrid[cRowIndex][cColumnIndex - 1].isVisited()){
                neighbors.add(pathGrid[cRowIndex][cColumnIndex - 1]);
                c.setLeftWall(false);
                pathGrid[cRowIndex][cColumnIndex - 1].setRightWall(false);
                //addCellIDToActions(c);
                //addCellIDToActions(BOARD[c.getROW()][c.getCOL() - 1]);
            }
        }
        if (cColumnIndex < (pathGridDimension - 1)){ //check right
            if (!pathGrid[cRowIndex][cColumnIndex + 1].isVisited()){
                neighbors.add(pathGrid[cRowIndex][cColumnIndex + 1]);
                c.setRightWall(false);
                pathGrid[cRowIndex][cColumnIndex + 1].setLeftWall(false);
                //addCellIDToActions(c);
                //addCellIDToActions(BOARD[c.getROW()][c.getCOL() + 1]);
            }
        }
        return neighbors;
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

//        // FIXME
//        System.out.println("First row: " + firstRowIndex);
//        System.out.println("Last row: " + lastRowIndex);
//        System.out.println("First column: " + firstColumnIndex);
//        System.out.println("Last column: " + lastColumnIndex);

        int wallRowIndex;
        int wallColumnIndex;

        if (firstRowIndex == lastRowIndex) {
            wallRowIndex = firstRowIndex;
            wallColumnIndex = firstColumnIndex + 1;

            if (firstColumnIndex == currentCell.getColumnIndex()) {
                currentCell.setRightWall(false);
                neighborCell.setLeftWall(false);
            } else {
                currentCell.setLeftWall(false);
                neighborCell.setRightWall(false);
            }
        } else {
            wallRowIndex = firstRowIndex + 1;
            wallColumnIndex = firstColumnIndex;

            if (firstRowIndex == currentCell.getRowIndex()) {
                currentCell.setDownWall(false);
                neighborCell.setUpWall(false);
            } else {
                currentCell.setUpWall(false);
                neighborCell.setDownWall(false);
            }
        }

//        // FIXME
//        System.out.println("Wall row: " + wallRowIndex);
//        System.out.println("Wall column: " + wallColumnIndex);

        mazeGrid[wallRowIndex][wallColumnIndex].setCellType(
                CellType.CELL_WALL_PATH);
    }

    public void backtrackCells(Cell currentCell, Cell backtrackCell) {
        // FIXME: problem at the corners...
        if (currentCell != backtrackCell) {
            int firstRowIndex = Math.min(currentCell.getRowIndex(),
                    backtrackCell.getRowIndex());
            int lastRowIndex = Math.max(currentCell.getRowIndex(),
                    backtrackCell.getRowIndex());
            int firstColumnIndex = Math.min(currentCell.getColumnIndex(),
                    backtrackCell.getColumnIndex());
            int lastColumnIndex = Math.max(currentCell.getColumnIndex(),
                    backtrackCell.getColumnIndex());

//            // FIXME
//            System.out.println("First row: " + firstRowIndex);
//            System.out.println("Last row: " + lastRowIndex);
//            System.out.println("First column: " + firstColumnIndex);
//            System.out.println("Last column: " + lastColumnIndex);

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
    }

    public Cell getWallPathInBetweenCells(Cell currentCell, Cell nextCell) {
        if (currentCell != nextCell) {
            int firstRowIndex = Math.min(currentCell.getRowIndex(),
                    nextCell.getRowIndex());
            int lastRowIndex = Math.max(currentCell.getRowIndex(),
                    nextCell.getRowIndex());
            int firstColumnIndex = Math.min(currentCell.getColumnIndex(),
                    nextCell.getColumnIndex());
            int lastColumnIndex = Math.max(currentCell.getColumnIndex(),
                    nextCell.getColumnIndex());

//            // FIXME
//            System.out.println("First row: " + firstRowIndex);
//            System.out.println("Last row: " + lastRowIndex);
//            System.out.println("First column: " + firstColumnIndex);
//            System.out.println("Last column: " + lastColumnIndex);

            int wallRowIndex;
            int wallColumnIndex;

            if (firstRowIndex == lastRowIndex) {
                wallRowIndex = firstRowIndex;
                wallColumnIndex = firstColumnIndex + 1;
            } else {
                wallRowIndex = firstRowIndex + 1;
                wallColumnIndex = firstColumnIndex;
            }

//            // FIXME
//            System.out.println("Wall row: " + wallRowIndex);
//            System.out.println("Wall column: " + wallColumnIndex);

            // FIXME: will only backtrack IF THE WALL CELL IS ALREADY A PATH
            if (mazeGrid[wallRowIndex][wallColumnIndex].getCellType()
                    == CellType.CELL_WALL_PATH) {
                return mazeGrid[wallRowIndex][wallColumnIndex];
            } else {
                System.out.println();
                System.out.println("Not a wall path...");
            }
        }

        return null;
    }

    public Cell getAnyWallInBetweenCells(Cell currentCell, Cell nextCell) {
        if (currentCell != nextCell) {
            int firstRowIndex = Math.min(currentCell.getRowIndex(),
                    nextCell.getRowIndex());
            int lastRowIndex = Math.max(currentCell.getRowIndex(),
                    nextCell.getRowIndex());
            int firstColumnIndex = Math.min(currentCell.getColumnIndex(),
                    nextCell.getColumnIndex());
            int lastColumnIndex = Math.max(currentCell.getColumnIndex(),
                    nextCell.getColumnIndex());

//            // FIXME
//            System.out.println("First row: " + firstRowIndex);
//            System.out.println("Last row: " + lastRowIndex);
//            System.out.println("First column: " + firstColumnIndex);
//            System.out.println("Last column: " + lastColumnIndex);

            int wallRowIndex;
            int wallColumnIndex;

            if (firstRowIndex == lastRowIndex) {
                wallRowIndex = firstRowIndex;
                wallColumnIndex = firstColumnIndex + 1;
            } else {
                wallRowIndex = firstRowIndex + 1;
                wallColumnIndex = firstColumnIndex;
            }

//            // FIXME
//            System.out.println("Wall row: " + wallRowIndex);
//            System.out.println("Wall column: " + wallColumnIndex);

            // FIXME: will only backtrack IF THE WALL CELL IS ALREADY A PATH
            return mazeGrid[wallRowIndex][wallColumnIndex];
        }

        // FIXME:
        System.out.println("Same cell for current/next cell");
        return null;
    }

    public void resetBacktrackToPath() {
        for (Cell[] cells : mazeGrid) {
            for (Cell cell : cells) {
                CellType cellType = cell.getCellType();

                if (cellType == CellType.CELL_PATH_BACKTRACK) {
                    cell.setCellType(CellType.CELL_PATH);
                } else if (cellType == CellType.CELL_WALL_BACKTRACK) {
                    cell.setCellType(CellType.CELL_WALL_PATH);
                }
            }
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

    public CellPath[][] getPathGrid() {
        return pathGrid;
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
