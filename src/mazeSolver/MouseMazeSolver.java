package mazeSolver;

import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

import java.util.ArrayList;
import java.util.Collections;

public class MouseMazeSolver extends MazeSolver {
    public MouseMazeSolver(MazeGrid mazeGrid, MazeSolverType mazeSolverType) {
        super(mazeGrid, mazeSolverType);
    }

    @Override
    public void solveMaze() {
        String directionTravelled = "down";
        String directionTravelling;
        ArrayList<String> openTravelOptions;

        DummyCell currentDummyCell = new DummyCell();
        currentDummyCell.addToDummy(startSolverCell);
        DummyCell nextDummyCell = new DummyCell();

        Cell c = currentDummyCell.getFromDummy();
        //addSolverLocation(c);

        Cell[][] pathGrid = mazeGrid.getPathGrid();

        while (c != endSolverCell) {
            openTravelOptions = mouseHelper(c);
            Collections.shuffle(openTravelOptions);

            CellPath cTemp = (CellPath) c;
            int cRowIndex = cTemp.getPathRowIndex();
            int cColumnIndex = cTemp.getPathColumnIndex();

            if (openTravelOptions.size() == 1) {
                directionTravelling = openTravelOptions.get(0);

                if (directionTravelling.equals("up")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex - 1][cColumnIndex]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "up";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());

                }
                if (directionTravelling.equals("right")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex][cColumnIndex + 1]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "right";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
                if (directionTravelling.equals("down")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex + 1][cColumnIndex]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "down";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
                if (directionTravelling.equals("left")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex][cColumnIndex - 1]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "left";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
            }
            if (openTravelOptions.size() > 1) {
                if (directionTravelled.equals("down") && openTravelOptions.get(0).equals("up")) {
                    openTravelOptions.remove(0);
                    directionTravelling = openTravelOptions.get(0);
                }
                if (directionTravelled.equals("up") && openTravelOptions.get(0).equals("down")){
                    openTravelOptions.remove(0);
                    directionTravelling = openTravelOptions.get(0);
                }
                if (directionTravelled.equals("right") && openTravelOptions.get(0).equals("left")){
                    openTravelOptions.remove(0);
                    directionTravelling = openTravelOptions.get(0);
                }
                if (directionTravelled.equals("left") && openTravelOptions.get(0).equals("right")){
                    openTravelOptions.remove(0);
                    directionTravelling = openTravelOptions.get(0);
                }

                directionTravelling = openTravelOptions.get(0);

//                if (directionTravelling.equals("up")) {
//                    c = pathGrid[cRowIndex - 1][cColumnIndex];
//                    directionTravelled = "up";
//                    //addSolverLocation(c);
//                }
//                if (directionTravelling.equals("right")) {
//                    c = pathGrid[cRowIndex][cColumnIndex + 1];
//                    directionTravelled = "right";
//                    //addSolverLocation(c);
//                }
//                if (directionTravelling.equals("down")) {
//                    c = pathGrid[cRowIndex + 1][cColumnIndex];
//                    directionTravelled = "down";
//                    //addSolverLocation(c);
//                }
//                if (directionTravelling.equals("left")) {
//                    c = pathGrid[cRowIndex][cColumnIndex - 1];
//                    directionTravelled = "left";
//                    //addSolverLocation(c);
//                }
                if (directionTravelling.equals("up")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex - 1][cColumnIndex]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "up";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());

                }
                if (directionTravelling.equals("right")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex][cColumnIndex + 1]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "right";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
                if (directionTravelling.equals("down")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex + 1][cColumnIndex]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "down";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
                if (directionTravelling.equals("left")) {
                    nextDummyCell.addToDummy(
                            pathGrid[cRowIndex][cColumnIndex - 1]);
                    c = nextDummyCell.getFromDummy();
                    directionTravelled = "left";
                    //addSolverLocation(c);
                    updateCellSolver(currentDummyCell.getFromDummy(),
                            nextDummyCell.getFromDummy());
                    currentDummyCell.addToDummy(
                            nextDummyCell.getFromDummy());
                }
            }

            openTravelOptions.clear();
        }
    }

    public ArrayList<String> mouseHelper(Cell c){
        ArrayList<String> openTravelOptions = new ArrayList<>();
        if (!c.isUpWall()){
            openTravelOptions.add("up");
        }
        if (!c.isRightWall()){
            openTravelOptions.add("right");
        }
        if (!c.isDownWall()){
            openTravelOptions.add("down");
        }
        if (!c.isLeftWall()){
            openTravelOptions.add("left");
        }
        return openTravelOptions;
    }
}
