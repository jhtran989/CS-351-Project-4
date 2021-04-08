package mazeGenerator;

import animationTimer.MazeAnimationTimer;
import mazePieces.Cell;
import mazePieces.CellType;
import mazePieces.MazeGrid;

import java.util.List;

public class DPSMazeGenerator extends MazeGenerator{
    public DPSMazeGenerator(MazeGrid mazeGrid,
                            MazeGeneratorType mazeGeneratorType) {
        super(mazeGrid, mazeGeneratorType
        );
    }

    @Override
    public void generateStartingPoint() {
        int randomRowIndex = threadLocalRandom.nextInt(
                mazeGrid.getDimension());
        int randomColumnIndex = threadLocalRandom.nextInt(
                mazeGrid.getDimension());

        startingCell = mazeGrid.getCell(randomRowIndex,
                randomColumnIndex);


        startingCell.setCellType(CellType.PATH);
        pathStack.push(startingCell);
    }

    @Override
    public void generateMaze(Cell startingCell) {
        startingCell.setCellType(CellType.PATH);
        pathStack.push(startingCell);

        while (!pathStack.isEmpty()) {
            Cell currentCell = pathStack.pop();
            List<Cell> currentNeighbors = mazeGrid.getNeighborList(
                    currentCell);

            if (!currentNeighbors.isEmpty()) {
                int randomIndex =
                        threadLocalRandom.nextInt(
                                currentNeighbors.size());

                int currentIndex = 0;
                for (Cell currentNeighbor : currentNeighbors) {
                    // TODO: this is where we could update the paths...
                    mazeGrid.connectCells(currentCell, currentNeighbor);

                    if (currentIndex != randomIndex) {
                        pathStack.push(currentNeighbor);
                    }

                    currentIndex++;
                }

                pathStack.push(currentNeighbors.get(randomIndex));
            } else { // update the cell as backtracked
                currentCell.setCellType(CellType.PATH_BACKTRACK);
            }
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
                        mazeGrid.connectCells(currentCell, currentNeighbor);
                    }

                    currentIndex++;
                }

                this.currentCell = currentNeighbors.get(randomIndex);
                pathStack.push(currentNeighbors.get(randomIndex));
            } else { // update the cell as backtracked
                currentCell.setCellType(CellType.PATH_BACKTRACK);
            }
        }
    }
}
