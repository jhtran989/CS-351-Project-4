package mazeGenerator;

/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This enum ...//TODO
 */

/**
 * Constants for the maze generator
 */
public enum MazeGeneratorType {
    DEPTH_FIRST_SEARCH("dfs"),
    KRUSKAL("kruskal"),
    PRIM("prim"),
    ALDOUS("aldous");

    private String stringRep;

    MazeGeneratorType(String stringRep) {
        this.stringRep = stringRep;
    }

    public static MazeGeneratorType getMazeGeneratorTypeFromString(
            String mazeGeneratorTypeString) {
        for (MazeGeneratorType mazeGeneratorType : values()) {
            if (mazeGeneratorType.stringRep.toLowerCase()
                    .equals(mazeGeneratorTypeString)) {
                return mazeGeneratorType;
            }
        }

        return null;
    }
}
