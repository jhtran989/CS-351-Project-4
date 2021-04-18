package mazeSolver;

import constants.CellType;
import constants.Direction;
import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

import java.util.List;

public class PledgeMazeSolver extends MazeSolver {
    private int numRightTurns; // each right turn is -1 to turn count
    private int numLeftTurns; // each left turn is +1 to turn count
    private int totalTurnCount; // should just be numLeftTurns - numRightTurns
    private boolean wrapAround;

    public PledgeMazeSolver(MazeGrid mazeGrid, MazeSolverType mazeSolverType) {
        super(mazeGrid, mazeSolverType);

        numRightTurns = 0;
        numLeftTurns = 0;
        calculateTotalTurnCount();

        wrapAround = false;
    }

    @Override
    public void solveMaze() {
        DummyCell currentDummyCell = new DummyCell();
        currentDummyCell.addToDummy(startSolverCell);
        DummyCell nextDummyCell = new DummyCell();

        Cell c = currentDummyCell.getFromDummy();
        //addSolverLocation(c);

        Cell[][] pathGrid = mazeGrid.getPathGrid();

        // (hug right wall)
        // prioritize COUNTERCLOCKWISE direction (there's probably a better
        // way,
        // like ordering the enums in a clockwise manner and shifting the
        // values() list to the specified direction...)
        String currentDirection = "south";
        Direction currentCustomDirection = Direction.DOWN;
        while (c != endSolverCell){
            CellPath cTemp = (CellPath) c;
            int cRowIndex = cTemp.getPathRowIndex();
            int cColumnIndex = cTemp.getPathColumnIndex();

            if (totalTurnCount == 0 && !c.checkWallInDirection(
                    currentCustomDirection)) {
                calculateTotalTurnCount();

                nextDummyCell.addToDummy(
                        mazeGrid.getNeighborInDirection(c,
                                currentCustomDirection));
                c = nextDummyCell.getFromDummy();
                updateCellSolver(currentDummyCell.getFromDummy(),
                        nextDummyCell.getFromDummy());
                currentDummyCell.addToDummy(
                        nextDummyCell.getFromDummy());
            } else {
//                switch (currentDirection) {
//                    case "south":
//                        if (!c.isLeftWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex - 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "west";
//                            break;
//                        } else if (!c.isDownWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex + 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "south";
//                            break;
//                        } else if (!c.isRightWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex + 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "east";
//                            break;
//                        } else if (!c.isUpWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex - 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "north";
//                            break;
//                        }
//                        break;
//                    case "east":
//                        if (!c.isDownWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex + 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "south";
//                            break;
//                        } else if (!c.isRightWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex + 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "east";
//                            break;
//                        } else if (!c.isUpWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex - 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "north";
//                            break;
//                        } else if (!c.isLeftWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex - 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "west";
//                            break;
//                        }
//                        break;
//                    case "north":
//                        if (!c.isRightWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex + 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "east";
//                            break;
//                        } else if (!c.isUpWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex - 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "north";
//                            break;
//                        } else if (!c.isLeftWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex - 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "west";
//                            break;
//                        } else if (!c.isDownWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex + 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "south";
//                            break;
//                        }
//                        break;
//                    case "west":
//                        if (!c.isUpWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex - 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "north";
//                            break;
//                        } else if (!c.isLeftWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex - 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "west";
//                            break;
//                        } else if (!c.isDownWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex + 1][cColumnIndex]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "south";
//                            break;
//                        } else if (!c.isRightWall()) {
//                            nextDummyCell.addToDummy(
//                                    pathGrid[cRowIndex][cColumnIndex + 1]);
//                            c = nextDummyCell.getFromDummy();
//                            updateCellSolver(currentDummyCell.getFromDummy(),
//                                    nextDummyCell.getFromDummy());
//                            currentDummyCell.addToDummy(
//                                    nextDummyCell.getFromDummy());
//
//                            currentDirection = "east";
//                            break;
//                        }
//                        break;
//                }

                List<Direction> directionCounterClockwiseList =
                        Direction.getCounterClockwiseDirectionList(
                                currentCustomDirection);

                Direction wallDirection =
                        Direction.getClockwiseDirection(
                                currentCustomDirection);
                Cell wallCell = mazeGrid.getRightWall(c,
                        currentCustomDirection);

                if (wallCell != Cell.CELL_OUT_OF_BOUNDS
                        && wallCell.getCellType() == CellType.CELL_WALL_PATH) {
                    currentCustomDirection = wallDirection;
                    numRightTurns++;

                    System.out.println("Right turn");
                } else {
                    int currentTurnCount = 0;
                    for (Direction direction : directionCounterClockwiseList) {
                        if (!c.checkWallInDirection(direction)) {
                            currentCustomDirection = direction;
                            break;
                        }

                        numLeftTurns++;
                        currentTurnCount++;
                    }

                    System.out.println(currentTurnCount + " left turns");
                }

                calculateTotalTurnCount();

                // FIXME:
                System.out.println("Current direction: " +
                        currentCustomDirection);

                assert currentCustomDirection != null;
                nextDummyCell.addToDummy(
                        mazeGrid.getNeighborInDirection(c,
                                currentCustomDirection));
                c = nextDummyCell.getFromDummy();
                updateCellSolver(currentDummyCell.getFromDummy(),
                        nextDummyCell.getFromDummy());
                currentDummyCell.addToDummy(
                        nextDummyCell.getFromDummy());
            }

            //addSolverLocation(c);
        }
    }

    private void calculateTotalTurnCount() {
        totalTurnCount = numLeftTurns - numRightTurns;

        // FIXME:
        System.out.println();
        System.out.println("Num left turns: " + numLeftTurns);
        System.out.println("Num right turns: " + numRightTurns);
        System.out.println("Total turn count: " + totalTurnCount);
    }
}
