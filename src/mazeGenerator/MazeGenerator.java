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
    protected Stack<Cell> pathStack;
    protected MazeGrid mazeGrid;
    protected MazeGeneratorType mazeGeneratorType;
    protected static ThreadLocalRandom threadLocalRandom =
            ThreadLocalRandom.current();
    protected Cell startingCell;
    protected Cell currentCell;

    public MazeGenerator(MazeGrid mazeGrid,
                         MazeGeneratorType mazeGeneratorType) {
        pathStack = new Stack<>();
        this.mazeGrid = mazeGrid;
        this.mazeGeneratorType = mazeGeneratorType;
    }

    public abstract void generateStartingPoint();
    public abstract void generateMaze(Cell startingCell);
    public abstract void generatePartOfMaze();
}
