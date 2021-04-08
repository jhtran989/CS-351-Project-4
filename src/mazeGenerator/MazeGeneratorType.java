package mazeGenerator;

/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This enum ...//TODO
 */

public enum MazeGeneratorType {
    DEPTH_FIRST_SEARCH("dfs"),
    KRUSKAL("kruskal");

    private String stringRep;

    MazeGeneratorType(String stringRep) {
        this.stringRep = stringRep;
    }
}
