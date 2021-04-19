package mazeGenerator;

import mazePieces.Cell;
import constants.CellType;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Depth First Search (DFS) Maze generator algorithm
 */
public class DFSMazeGenerator extends MazeGenerator{
    public DFSMazeGenerator(MazeGrid mazeGrid,
                            MazeGeneratorType mazeGeneratorType) {
        super(mazeGrid, mazeGeneratorType);
    }

    @Override
    public void generateStartingPoint() {
        int randomRowIndex = threadLocalRandom.nextInt(
                mazeGrid.getPathGridDimension());
        int randomColumnIndex = threadLocalRandom.nextInt(
                mazeGrid.getPathGridDimension());

        startingCell = mazeGrid.getCellPath(randomRowIndex,
                randomColumnIndex);

        startingCell.setCellType(CellType.CELL_PATH);
        ((CellPath) startingCell).activateVisited();

        pathStack.push(startingCell);
        cellVisit.push(startingCell);

        //mazeGeneratorDEBUG.printMazeGrid();
    }

//    @Override
//    public void generateMaze(Cell startingCell) {
//        startingCell.setCellType(CellType.CELL_PATH);
//        pathStack.push(startingCell);
//
//        while (!pathStack.isEmpty()) {
//            Cell currentCell = pathStack.pop();
//            List<Cell> currentNeighbors = mazeGrid.getNeighborList(
//                    currentCell);
//
//            if (!currentNeighbors.isEmpty()) {
//                int randomIndex =
//                        threadLocalRandom.nextInt(
//                                currentNeighbors.size());
//
//                int currentIndex = 0;
//                for (Cell currentNeighbor : currentNeighbors) {
//                    // TODO: this is where we could update the paths...
//                    mazeGrid.connectCells(currentCell,
//                            currentNeighbor);
//
//                    if (currentIndex != randomIndex) {
//                        pathStack.push(currentNeighbor);
//                    }
//
//                    currentIndex++;
//                }
//
//                pathStack.push(currentNeighbors.get(randomIndex));
//            } else { // update the cell as backtracked
//                currentCell.setCellType(CellType.CELL_PATH_BACKTRACK);
//            }
//        }
//    }

    @Override
    public void generateMaze(Cell startingCell) {
        // FIXME: not random yet...
        // added dummy cell
        DummyCell dummyCell = new DummyCell();
        dummyCell.addToDummy((CellPath) startingCell);
        Cell c = dummyCell.getFromDummy();
        c.setPreviousCell(c); // important for backtracking

        System.out.println("Original " + startingCell);
        System.out.println("New " + dummyCell.getFromDummy());
        // FIXME: helps with the GUI...update edges
//        ArrayList<Edge> edges = getAllEdges();

        pathStack.push(c);
        //c.updateCellPath();
        c.setCellType(CellType.CELL_PATH);
        List<Cell> neighbors;

        //neighbors = mazeGrid.getNeighborList(c);
        neighbors = mazeGrid.getNeighborsBreakWalls(c);

        Collections.shuffle(neighbors);
        for (Cell neighbor : neighbors) {
            neighbor.setPreviousCell(dummyCell.getFromDummy());
            mazeGrid.connectCells(neighbor,
                    neighbor.getPreviousCell());
            pathStack.push(neighbor);
            //neighbor.visit();
            ((CellPath) neighbor).activateVisited();
        }
        //mazeGeneratorDEBUG.printMazeGrid();
        neighbors.clear();
        while(!pathStack.empty()){
            dummyCell.addToDummy(pathStack.pop());
            c = dummyCell.getFromDummy();
            //c.setPreviousCell(dummyCell.getFromDummy());
            if (((CellPath) c).isVisited()) {
                //c.updateCellPath();
                c.setCellType(CellType.CELL_PATH);
            }
            //mazeGeneratorDEBUG.printMazeGrid();

            //neighbors = mazeGrid.getNeighborList(c);
            neighbors = mazeGrid.getNeighborsBreakWalls(c);

            Collections.shuffle(neighbors);
            for (Cell neighbor : neighbors) {
                neighbor.setPreviousCell(dummyCell.getFromDummy());
                mazeGrid.connectCells(neighbor,
                        neighbor.getPreviousCell());
                pathStack.push(neighbor);
                //neighbor.visit();
                ((CellPath) neighbor).activateVisited();
            }
            if (!pathStack.isEmpty()) {
                Cell nextCell;
                nextCell = pathStack.pop();
                dummyCell.addToDummy(nextCell);
                pathStack.push(nextCell);

                if (neighbors.isEmpty()) {
                    //c.updateCellPathBacktrack();
                    c.setCellType(CellType.CELL_PATH_BACKTRACK);
                    nextCell = pathStack.pop();
                    System.out.println("Next cell " + nextCell);
                    Cell returnCell = nextCell.getPreviousCell();

                    // FIXME
                    DummyCell currentBacktrack = new DummyCell();
                    currentBacktrack.addToDummy(c);

                    Cell previousCell = c.getPreviousCell();
                    mazeGrid.backtrackCells(
                            currentBacktrack.getFromDummy(),
                            previousCell);

                    // FIXME
                    //mazeGeneratorDEBUG.printMazeGridAddresses();

                    System.out.println("Current cell " + c);
                    System.out.println("Return cell " + returnCell);
                    // FIXME
                    System.out.println("Previous cell " + previousCell);
                    while (previousCell != returnCell) {
                        //previousCell.updateCellPathBacktrack();
                        previousCell.setCellType(CellType.CELL_PATH_BACKTRACK);

                        currentBacktrack.addToDummy(
                                currentBacktrack.getFromDummy()
                                        .getPreviousCell());
                        previousCell = previousCell.getPreviousCell();

                        mazeGrid.backtrackCells(
                                currentBacktrack.getFromDummy(),
                                previousCell);
                        // FIXME
                        System.out.println("Previous cell " + previousCell);
                    }
                    pathStack.push(nextCell);
                    // FIXME: also update all cells along path to last cell with
                    //  more neighbors to check...maybe add previous cell field
                    //  to each cell so we "connect" each cell with the cell that
                    //  connects to it
                    //mazeGeneratorDEBUG.printMazeGrid();
                }
            } else {
                System.out.println("Last backtrack...");
                System.out.println("Current " + c);
                //c.updateCellPathBacktrack();
                c.setCellType(CellType.CELL_PATH_BACKTRACK);
                //mazeGeneratorDEBUG.printMazeGrid();
            }
//            printBoard();
            neighbors.clear();
        }
    }

    @Override
    public void generatePartOfMaze() {
        if (!pathStack.isEmpty()) {
            Cell currentCell = pathStack.pop();

            // FIXME: keeps track of the current cell
            this.currentCell = currentCell;

            List<Cell> currentNeighbors = mazeGrid.getNeighborList(
                    currentCell);
            currentCell.setNeighbors(currentNeighbors);

            // FIXME
            mazeGeneratorDEBUG.printDPS();

            if (!currentNeighbors.isEmpty()) {
                int randomIndex =
                        threadLocalRandom.nextInt(
                                currentNeighbors.size());

                int currentIndex = 0;
                for (Cell currentNeighbor : currentNeighbors) {
                    // TODO: this is where we could update the paths...
                    // FIXME: connect only the neighbor with the chosen
                    //  random index

                    if (currentIndex != randomIndex) {
                        pathStack.push(currentNeighbor);
                    } else {
                        //FIXME
                        System.out.println("Chosen neighbor: "
                                + currentNeighbor);
                    }

                    // Will look weird...
                    mazeGrid.connectCells(currentCell,
                            currentNeighbor);

                    //currentNeighbor.activateVisited();
                    currentIndex++;
                }

                this.currentCell = currentNeighbors.get(randomIndex);
                //this.previousCell = currentCell;
                pathStack.push(currentNeighbors.get(randomIndex));
                cellVisit.push(currentNeighbors.get(randomIndex));

                // FIXME
                mazeGeneratorDEBUG.printDPS();
            } else { // update the cell as backtracked
                cellVisit.pop();
                Cell backtrackCell = cellVisit.pop();

                System.out.println();
                System.out.println("Backtrack");
                System.out.println("Current cell: " + currentCell);
                System.out.println("Backtrack: " + backtrackCell);

                mazeGrid.backtrackCells(currentCell,
                        backtrackCell);

//                if (currentCell.equals(backtrackCell)) {
//                    // FIXME
//                    System.out.println();
//                    System.out.println("Backtrack");
//                    System.out.println("Current cell: " + currentCell);
//                    System.out.println("Backtrack: " + backtrackCell);
//
//                    mazeGrid.backtrackCells(currentCell,
//                            backtrackCell);
//                } else {
//                    // FIXME
//                    System.out.println();
//                    System.out.println("No backtrack...");
//                }

                cellVisit.push(backtrackCell);
            }
        }
    }
}
