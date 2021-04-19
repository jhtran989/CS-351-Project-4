package mazeGenerator;

import constants.CellType;
import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

import java.util.List;

public class AldousBroderMazeGenerator extends MazeGenerator {
    public AldousBroderMazeGenerator(MazeGrid mazeGrid,
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

        mazeGeneratorDEBUG.printMazeGrid();
    }

    @Override
    public void generateMaze(Cell startingCell) {
        int pathDimension = mazeGrid.getPathGridDimension();
        int numPathCellsToCheck = pathDimension * pathDimension - 1;

        DummyCell currentDummyCell = new DummyCell();
        currentDummyCell.addToDummy(startingCell);
        Cell c = currentDummyCell.getFromDummy();
        CellPath cellPath = (CellPath) c;
        cellPath.activateVisited();

        while (numPathCellsToCheck > 0) {
            List<Cell> allNeighbors = mazeGrid.getAllNeighborList(c);
            int randomIndex = threadLocalRandom.nextInt(
                    allNeighbors.size());

            DummyCell neighborDummyCell = new DummyCell();
            neighborDummyCell.addToDummy(allNeighbors.get(randomIndex));

            Cell randomNeighbor = neighborDummyCell.getFromDummy();

            CellPath randomNeighborPath = (CellPath) randomNeighbor;
            if (!randomNeighborPath.isVisited()) {
                mazeGrid.connectCells(c,
                        randomNeighbor);
                randomNeighborPath.activateVisited();
                numPathCellsToCheck--;
            }

            updateCellGenerator(c, randomNeighbor);

            c = neighborDummyCell.getFromDummy();
        }

        c.setCellType(CellType.CELL_PATH);
    }

    @Override
    public void generatePartOfMaze() {

    }
}
