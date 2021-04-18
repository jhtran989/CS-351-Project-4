package mazeGenerator;

import constants.CellType;
import mazePieces.Cell;
import mazePieces.Edge;
import mazePieces.MazeGrid;
import utilities.UnionFind;

import java.util.Collections;
import java.util.List;

public class KruskalMazeGenerator extends MazeGenerator {
    private List<Edge> edgesList;

    public KruskalMazeGenerator(MazeGrid mazeGrid,
                                MazeGeneratorType mazeGeneratorType,
                                List<Edge> edgesList) {
        super(mazeGrid, mazeGeneratorType);
        this.edgesList = edgesList;
    }

    @Override
    public void generateStartingPoint() {

    }

    @Override
    public void generateMaze(Cell startingCell) {
        int pathDimension = mazeGrid.getPathGridDimension();
        UnionFind uf = new UnionFind(pathDimension * pathDimension);
        Collections.shuffle(edgesList);

        while(!edgesList.isEmpty()){
            //FIXME:


            if (!uf.connected(edgesList.get(0).getCELL_ONE().getCellID(),
                    edgesList.get(0).getCELL_TWO().getCellID())){
                uf.union(edgesList.get(0).getCELL_ONE().getCellID(),
                        edgesList.get(0).getCELL_TWO().getCellID());
                if (edgesList.get(0).isHORIZONTAL()){
                    edgesList.get(0).takeDownHorizontalWall();
                    edgesList.get(0).getCELL_ONE().setCellType(
                            CellType.CELL_PATH);
                    edgesList.get(0).getCELL_TWO().setCellType(
                            CellType.CELL_PATH);

                    edgesList.get(0).getCellWall().setCellType(
                            CellType.CELL_WALL_PATH);
                }

                if (edgesList.get(0).isVERTICAL()){
                    edgesList.get(0).takeDownVerticalWall();
                    edgesList.get(0).getCELL_ONE().setCellType(
                            CellType.CELL_PATH);
                    edgesList.get(0).getCELL_TWO().setCellType(
                            CellType.CELL_PATH);

                    edgesList.get(0).getCellWall().setCellType(
                            CellType.CELL_WALL_PATH);
                }

                edgesList.remove(0);
            } else {
                edgesList.remove(0);
            }
        }

        // FIXME: implement choose solver
        //chooseSolver(solver);
    }

    @Override
    public void generatePartOfMaze() {

    }
}
