package mazeSolver;

import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

/**
 * Implementation of the Wall (follower) Maze solver algorithm
 */
public class WallMazeSolver extends MazeSolver {
    public WallMazeSolver(MazeGrid mazeGrid, MazeSolverType mazeSolverType) {
        super(mazeGrid, mazeSolverType);
    }

    @Override
    public void solveMaze() {
        DummyCell currentDummyCell = new DummyCell();
        currentDummyCell.addToDummy(startSolverCell);
        DummyCell nextDummyCell = new DummyCell();

        Cell c = currentDummyCell.getFromDummy();
        //addSolverLocation(c);

        Cell[][] pathGrid = mazeGrid.getPathGrid();

        //FIXME: need to add threads...

        // START at the BEGINNING (hug left wall)
        // prioritize CLOCKWISE direction (there's probably a better way,
        // like ordering the enums in a clockwise manner and shifting the
        // values() list to the specified direction...)
        String currentDirection = "south";
        while (c != endSolverCell){
            CellPath cTemp = (CellPath) c;
            int cRowIndex = cTemp.getPathRowIndex();
            int cColumnIndex = cTemp.getPathColumnIndex();

            switch (currentDirection) {
                case "south":
                    if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    }
                    break;
                case "east":
                    if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    }
                    break;
                case "north":
                    if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    }
                    break;
                case "west":
                    if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    }
                    break;
            }
            //addSolverLocation(c);
        }

        // START at the END (hug right wall)
        // prioritize COUNTERCLOCKWISE direction (there's probably a better
        // way,
        // like ordering the enums in a clockwise manner and shifting the
        // values() list to the specified direction...)
        currentDirection = "north";
        while (c != startSolverCell){
            CellPath cTemp = (CellPath) c;
            int cRowIndex = cTemp.getPathRowIndex();
            int cColumnIndex = cTemp.getPathColumnIndex();

            switch (currentDirection) {
                case "south":
                    if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    }
                    break;
                case "east":
                    if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    }
                    break;
                case "north":
                    if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    } else if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    }
                    break;
                case "west":
                    if (!c.isUpWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex - 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "north";
                        break;
                    } else if (!c.isLeftWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex - 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "west";
                        break;
                    } else if (!c.isDownWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex + 1][cColumnIndex]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "south";
                        break;
                    } else if (!c.isRightWall()) {
                        nextDummyCell.addToDummy(
                                pathGrid[cRowIndex][cColumnIndex + 1]);
                        c = nextDummyCell.getFromDummy();
                        updateCellSolver(currentDummyCell.getFromDummy(),
                                nextDummyCell.getFromDummy());
                        currentDummyCell.addToDummy(
                                nextDummyCell.getFromDummy());

                        currentDirection = "east";
                        break;
                    }
                    break;
            }
            //addSolverLocation(c);
        }
    }
}
