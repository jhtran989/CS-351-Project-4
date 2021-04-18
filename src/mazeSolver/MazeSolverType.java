package mazeSolver;

/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This enum ...//TODO
 */

public enum MazeSolverType {
    MOUSE("mouse"),
    MOUSE_THREAD("mouse_thread"),
    WALL("wall"),
    PLEDGE("pledge");

    private String stringRep;

    MazeSolverType(String stringRep) {
        this.stringRep = stringRep;
    }

    public static MazeSolverType getMazeSolverTypeFromString(
            String mazeSolverTypeString) {
        for (MazeSolverType mazeSolverType : values()) {
            if (mazeSolverType.stringRep.toLowerCase()
                    .equals(mazeSolverTypeString)) {
                return mazeSolverType;
            }
        }

        return null;
    }
}
