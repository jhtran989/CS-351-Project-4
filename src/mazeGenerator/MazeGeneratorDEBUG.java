package mazeGenerator;

import mazePieces.MazeGrid;

/**
 * Holds all the DEBUG statements
 */
public class MazeGeneratorDEBUG {
    private static boolean DFS = true;

    private final MazeGenerator mazeGenerator;
    private final MazeGrid mazeGrid;

    public MazeGeneratorDEBUG(MazeGenerator mazeGenerator) {
        this.mazeGenerator = mazeGenerator;
        mazeGrid = mazeGenerator.mazeGrid;
    }

    public void printDPS() {
        if (DFS) {
            System.out.println();
            System.out.println("Path stack: " + mazeGenerator.getPathStack());
            System.out.println("Cell visit stack: " +
                    mazeGenerator.getCellVisit());
            System.out.println("Current cell: "
                    + mazeGenerator.getCurrentCell());
            System.out.println("Current neighbors");
            System.out.println(mazeGenerator.getCurrentCell().getNeighbors());
        }
    }

    public void printMazeGrid() {
        mazeGrid.printMazeGrid();
        mazeGrid.printPathGrid();
    }

    public void printMazeGridAddresses() {
        mazeGrid.printMazeGridAddresses();
    }
}
