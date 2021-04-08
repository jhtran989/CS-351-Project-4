package mazeGenerator;

import mazePieces.Cell;
import mazePieces.CellType;

import java.util.List;

public class DPSMazeGenerator extends MazeGenerator{
    @Override
    public void generateMaze(Cell startingCell) {
        pathStack.push(startingCell);

        while (!pathStack.isEmpty()) {
            Cell currentCell = pathStack.pop();
            List<Cell> currentNeighbors = mazeGrid.getNeighborList(
                    currentCell);

            if (!currentNeighbors.isEmpty()) {
                int randomIndex =
                        threadLocalRandom.nextInt(
                                currentNeighbors.size());

                for (Cell currentNeighbor : currentNeighbors) {
                    // TODO: this is where we could update the paths...
                    currentNeighbor.setCellType(CellType.PATH_VISITED);

                    // TODO: Finish...
                }
            }
        }
    }
}
