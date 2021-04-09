package mazeGenerator;

import mazePieces.Cell;
import mazePieces.MazeGrid;

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
