/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class is the main class for the Maze program.
 */

import animationTimer.CellActionSequence;
import animationTimer.MazeAnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mazeGenerator.MazeGenerator;
import mazePieces.Cell;
import mazePieces.MazeGrid;
import mazeSolver.MazeSolver;

import java.io.*;
import java.util.Scanner;

public class MazesMain extends Application {
    private static int mazeSize;
    private static int cellSize;
    private static int mazeGridDimension;
    private static String mazeGeneratorChoice;
    private static String mazeSolverChoice;

    /***
     * main method.
     *
     * @param args - A file with directions is to be passed in with the format:
     *             int // size of the maze.
     *             int // size of the cells in the maze.
     *             String // the algorithm to create the maze.
     *             String // the algorithm type to solve the maze.
     * @throws IOException - typical io safeguard.
     */
    public static void main(String[] args) throws IOException {
        //readTheFile(args[0]);
        readInputStreamReader(
                new InputStreamReader(
                        MazesMain.class.getResourceAsStream(
                                "example_input_9.txt")));
        launch(args);
    }

    /***
     * Program start, runs the gui.
     * @param primaryStage is the primary stage for the program.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // test
        primaryStage.setTitle("Mazes");
        Canvas canvas = new Canvas(mazeSize, mazeSize);
        Pane root = new Pane(canvas);

        // FIXME: create a MazeBoard object instead
//        Cell cell = new Cell(root, cellSize, Color.BLUE, true, true,
//                true, true, rowIndex, columnIndex);

        // default to WITH OUTLINE
        // REMEMBER TO PUT OUTLINE TO THE SAME VALUE FOR BOTH GRIDS
        MazeGrid mazeGridGUI = new MazeGrid(mazeGridDimension,
                cellSize, false, false,
                null);
        MazeGrid internalMazeGrid = new MazeGrid(
                mazeGridDimension, cellSize,
                false, true, mazeGridGUI);
        MazeGenerator mazeGenerator =
                MazeGenerator.getMazeGeneratorFactory(
                        mazeGeneratorChoice,
                        internalMazeGrid);
        MazeSolver mazeSolver =
                MazeSolver.getMazeSolverFactory(mazeSolverChoice,
                        internalMazeGrid);

        CellActionSequence cellActionSequence =
                internalMazeGrid.getCellActionSequence();
        MazeAnimationTimer mazeAnimationTimer = new MazeAnimationTimer(root,
                mazeGridGUI, mazeGenerator,
                cellActionSequence);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        if (mazeGenerator != null) {
            mazeGenerator.generateStartingPoint();
            Cell startingCell = mazeGenerator.getStartingCell();
            mazeGenerator.generateMaze(startingCell);

            // Should only be used for DFS
            internalMazeGrid.resetBacktrackToPath();

            //TODO: Add start and end points
            if (mazeSolver != null) {
                mazeSolver.generateStartEndMazePoints();
                mazeSolver.solveMaze();
            } else {
                System.out.println();
                System.out.println("File input error...solver");
            }

            mazeAnimationTimer.start();
            mazeAnimationTimer.run();
        } else {
            System.out.println();
            System.out.println("File input error...generator");
        }
    }

    /***
     * readTheFile exists to read in the input file for directions on how to
     * create and solve the maze.
     *
     * @param args - args[0] passed in from main method, the file name.
     * @throws IOException - typical io safeguard.
     */
    public static void readTheFile(String args) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(args))) {
            mazeSize = Integer.parseInt(br.readLine());
            cellSize = Integer.parseInt(br.readLine());
            String algorithm = br.readLine();
            String solver = br.readLine();
            System.out.println(mazeSize);
            System.out.println(cellSize);
            System.out.println(algorithm);
            System.out.println(solver);
        }
    }

    /**
     * Updated so the dimension is also calculated (assuming the overall size
     * of the window divides the cell size evenly...)
     * @param inputStreamReader
     */
    public static void readInputStreamReader(InputStreamReader
                                                     inputStreamReader) {
        try (Scanner scanner = new Scanner(inputStreamReader)) {
            mazeSize = Integer.parseInt(scanner.nextLine());
            cellSize = Integer.parseInt(scanner.nextLine());
            mazeGridDimension = mazeSize / cellSize;
            mazeGeneratorChoice = scanner.nextLine();
            mazeSolverChoice = scanner.nextLine();
            System.out.println(mazeSize);
            System.out.println(cellSize);
            System.out.println(mazeGeneratorChoice);
            System.out.println(mazeSolverChoice);
        }
    }
}
