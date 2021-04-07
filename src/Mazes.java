/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class is the main class for the Maze program.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Mazes extends Application {
    private static int mazeSize;
    private static int cellSize;

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
        readTheFile(args[0]);
        launch(args);
    }

    /***
     * Program start, runs the gui.
     * @param primaryStage is the primary stage for the program.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mazes");
        Canvas canvas = new Canvas(mazeSize, mazeSize);
        Pane root = new Pane(canvas);

        Cell cell = new Cell(root, cellSize, Color.BLUE, true, true,
                true, true);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /***
     * readTheFile exists to read in the input file for directions on how to
     * create and solve the maze.
     *
     * @param args - args[0] passed in from main method, the file name.
     * @throws IOException - typical io safeguard.
     */
    public static void readTheFile(String args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(args))) {
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


}
