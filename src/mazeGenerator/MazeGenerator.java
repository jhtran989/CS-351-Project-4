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

    public abstract void generateMaze(Cell startingCell);
}
