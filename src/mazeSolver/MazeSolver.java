package mazeSolver;

import mazeGenerator.*;
import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class ...//TODO
 */

public abstract class MazeSolver {
    protected MazeGrid mazeGrid;
    protected static ThreadLocalRandom threadLocalRandom =
            ThreadLocalRandom.current();
    protected Cell startSolverCell;
    protected Cell endSolverCell;
    protected MazeSolverType mazeSolverType;

    public void generateStartEndMazePoints() {
        Cell[][] pathGrid = mazeGrid.getPathGrid();
        int pathDimension = mazeGrid.getPathGridDimension();

        List<Cell> possibleSolverCellList = new ArrayList<>();

        int lastIndex = pathDimension - 1;
        for (int i = 0; i < pathDimension; i++) {
            for (int j = 0; j < pathDimension; j++) {
                if (i == 0 || j == 0 || i == lastIndex || j == lastIndex) {
                    possibleSolverCellList.add(pathGrid[i][j]);
                }
            }
        }

        int startRandomIndex =
                threadLocalRandom.nextInt(possibleSolverCellList.size());
        startSolverCell = possibleSolverCellList.get(startRandomIndex);
        possibleSolverCellList.remove(startRandomIndex);
        startSolverCell.initializeStartPointSolver();

        CellPath startSolverCellPath = (CellPath) startSolverCell;
        int startRowIndex = startSolverCellPath.getPathRowIndex();
        int startColumnIndex = startSolverCellPath.getPathColumnIndex();

        Iterator<Cell> possibleSolverCellIterator =
                possibleSolverCellList.listIterator();
        if (startRowIndex == 0) {
            while (possibleSolverCellIterator.hasNext()) {
                CellPath nextCell = (CellPath) possibleSolverCellIterator.next();

                if (nextCell.getPathRowIndex() != pathDimension - 1) {
                    possibleSolverCellIterator.remove();
                }
            }
        } else if (startRowIndex == pathDimension - 1) {
            while (possibleSolverCellIterator.hasNext()) {
                CellPath nextCell = (CellPath) possibleSolverCellIterator.next();

                if (nextCell.getPathRowIndex() != 0) {
                    possibleSolverCellIterator.remove();
                }
            }
        } else if (startColumnIndex == 0) {
            while (possibleSolverCellIterator.hasNext()) {
                CellPath nextCell = (CellPath) possibleSolverCellIterator.next();

                if (nextCell.getPathColumnIndex() != pathDimension - 1) {
                    possibleSolverCellIterator.remove();
                }
            }
        } else { // has to be that startColumnIndex = pathDimension - 1
            while (possibleSolverCellIterator.hasNext()) {
                CellPath nextCell = (CellPath) possibleSolverCellIterator.next();

                if (nextCell.getPathColumnIndex() != 0) {
                    possibleSolverCellIterator.remove();
                }
            }
        }

        int endRandomIndex =
                threadLocalRandom.nextInt(possibleSolverCellList.size());
        endSolverCell = possibleSolverCellList.get(endRandomIndex);
        possibleSolverCellList.remove(endRandomIndex);
        endSolverCell.initializeEndPointSolver();
    }

    public static MazeSolver getMazeSolverFactory(
            String mazeSolverString, MazeGrid mazeGrid) {
        MazeSolverType mazeSolverType =
                MazeSolverType.getMazeSolverTypeFromString(
                        mazeSolverString);

        if (mazeSolverType != null) {
            switch (mazeSolverType) {
                case MOUSE:
                case MOUSE_THREAD:
                case WALL:
                default:
            }
        }

        return null;
    }

    public Cell getStartSolverCell() {
        return startSolverCell;
    }

    public Cell getEndSolverCell() {
        return endSolverCell;
    }
}
