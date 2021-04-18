package mazeGenerator;

import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class ...//TODO
 */

public abstract class MazeGenerator {
    // DEBUG members
    protected MazeGeneratorDEBUG mazeGeneratorDEBUG;

    protected Stack<Cell> pathStack;
    protected Stack<Cell> cellVisit;
    protected MazeGrid mazeGrid;
    protected MazeGeneratorType mazeGeneratorType;
    protected static ThreadLocalRandom threadLocalRandom =
            ThreadLocalRandom.current();
    protected Cell startingCell;
    protected Cell currentCell;
    protected Cell previousCell;

    public MazeGenerator(MazeGrid mazeGrid,
                         MazeGeneratorType mazeGeneratorType) {
        pathStack = new Stack<>();
        cellVisit = new Stack<>();
        this.mazeGrid = mazeGrid;
        this.mazeGeneratorType = mazeGeneratorType;

        // Haven't tried initializing something with this...something bad
        // might happen
        mazeGeneratorDEBUG = new MazeGeneratorDEBUG(this);
    }

    public abstract void generateStartingPoint();
    public abstract void generateMaze(Cell startingCell);
    public abstract void generatePartOfMaze();

    public static MazeGenerator getMazeGeneratorFactory(
            String mazeGeneratorString, MazeGrid mazeGrid) {
        MazeGeneratorType mazeGeneratorType =
                MazeGeneratorType.getMazeGeneratorTypeFromString(
                        mazeGeneratorString);

        if (mazeGeneratorType != null) {
            switch (mazeGeneratorType) {
                case DEPTH_FIRST_SEARCH:
                    return new DPSMazeGenerator(mazeGrid,
                            mazeGeneratorType);
                case KRUSKAL:
                    return new KruskalMazeGenerator(mazeGrid,
                            mazeGeneratorType,
                            mazeGrid.getAllEdges());
                case PRIM:
                    return new PrimMazeGenerator(mazeGrid,
                            mazeGeneratorType);
                default:
            }
        }

        return null;
    }

    public Stack<Cell> getPathStack() {
        return pathStack;
    }

    public Stack<Cell> getCellVisit() {
        return cellVisit;
    }

    public MazeGrid getMazeGrid() {
        return mazeGrid;
    }

    public MazeGeneratorType getMazeGeneratorType() {
        return mazeGeneratorType;
    }

    public Cell getStartingCell() {
        return startingCell;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }
}
