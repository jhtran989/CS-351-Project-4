package mazeGenerator;

import constants.CellType;
import mazePieces.Cell;
import mazePieces.CellPath;
import mazePieces.MazeGrid;
import utilities.DummyCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimMazeGenerator extends MazeGenerator {
    private boolean start;

    public PrimMazeGenerator(MazeGrid mazeGrid,
                             MazeGeneratorType mazeGeneratorType) {
        super(mazeGrid, mazeGeneratorType);

        start = true;
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

        mazeGeneratorDEBUG.printMazeGrid();
    }

    @Override
    public void generateMaze(Cell startingCell) {
        Cell[][] pathGrid = mazeGrid.getPathGrid();
        int pathDimension = mazeGrid.getPathGridDimension();

        ArrayList<String> directionChoices = new ArrayList<>();
        String direction;
        Cell c = startingCell;
        ((CellPath) c).activateVisited();
        List<Cell> neighbors = mazeGrid.getNeighborList(c);

        while(!neighbors.isEmpty()){
            Collections.shuffle(neighbors);

            if (start) {
                DummyCell dummyCell = new DummyCell();
                dummyCell.addToDummy(neighbors.get(0));
                Cell nextNeighbor = dummyCell.getFromDummy();
                mazeGrid.connectCells(c, nextNeighbor);
                start = false;

                // FIXME:
                mazeGeneratorDEBUG.printMazeGrid();
            }

            c = neighbors.get(0);
            ((CellPath) c).activateVisited();

            neighbors.remove(0);
            List<Cell> temp = mazeGrid.getNeighborList(c);
            for (Cell cell : temp) {
                if (!neighbors.contains(cell)) {
                    neighbors.add(cell);
                }
            }

            CellPath cTemp = (CellPath) c;
            int cTempRowIndex = cTemp.getPathRowIndex();
            int cTempColumnIndex = cTemp.getPathColumnIndex();

            if (cTempRowIndex > 0){ //check up
                if (((CellPath) pathGrid[cTempRowIndex - 1][cTempColumnIndex]).isVisited()){
                    directionChoices.add("north");
                }
            }
            if (cTempRowIndex < pathDimension - 1){ //check down
                if (((CellPath) pathGrid[cTempRowIndex + 1][cTempColumnIndex]).isVisited()){
                    directionChoices.add("south");
                }
            }
            if (cTemp.getPathColumnIndex() > 0){ //check left
                if (((CellPath) pathGrid[cTempRowIndex][cTempColumnIndex - 1]).isVisited()){
                    directionChoices.add("west");
                }

            }
            if (cTempColumnIndex < pathDimension - 1){ //check right
                if (((CellPath) pathGrid[cTempRowIndex][cTempColumnIndex + 1]).isVisited()){
                    directionChoices.add("east");
                }
            }
            Collections.shuffle(directionChoices);
            direction = directionChoices.get(0);
            if (direction.equals("north")){
                c.setUpWall(false);
                pathGrid[cTempRowIndex - 1][cTempColumnIndex].setDownWall(false);
                c.setCellType(CellType.CELL_PATH);
                Cell nextCell = pathGrid[cTempRowIndex - 1][cTempColumnIndex];
                nextCell.setCellType(CellType.CELL_PATH);
                mazeGrid.connectCells(c, nextCell);
                // FIXME:
                mazeGeneratorDEBUG.printMazeGrid();
            }
            if (direction.equals("south")){
                c.setDownWall(false);
                pathGrid[cTempRowIndex + 1][cTempColumnIndex].setUpWall(false);
                c.setCellType(CellType.CELL_PATH);
                Cell nextCell = pathGrid[cTempRowIndex + 1][cTempColumnIndex];
                nextCell.setCellType(CellType.CELL_PATH);
                mazeGrid.connectCells(c, nextCell);
                // FIXME:
                mazeGeneratorDEBUG.printMazeGrid();
            }
            if (direction.equals("west")){
                c.setLeftWall(false);
                pathGrid[cTempRowIndex][cTempColumnIndex - 1].setRightWall(false);
                c.setCellType(CellType.CELL_PATH);
                Cell nextCell = pathGrid[cTempRowIndex][cTempColumnIndex - 1];
                nextCell.setCellType(CellType.CELL_PATH);
                mazeGrid.connectCells(c, nextCell);
                // FIXME:
                mazeGeneratorDEBUG.printMazeGrid();
            }
            if (direction.equals("east")){
                c.setRightWall(false);
                pathGrid[cTempRowIndex][cTempColumnIndex + 1].setLeftWall(false);
                c.setCellType(CellType.CELL_PATH);
                Cell nextCell = pathGrid[cTempRowIndex][cTempColumnIndex + 1];
                nextCell.setCellType(CellType.CELL_PATH);
                mazeGrid.connectCells(c, nextCell);
                // FIXME:
                mazeGeneratorDEBUG.printMazeGrid();
            }

            directionChoices.clear();
        }

        // FIXME:
        //chooseSolver(solver);
    }

    @Override
    public void generatePartOfMaze() {

    }
}
