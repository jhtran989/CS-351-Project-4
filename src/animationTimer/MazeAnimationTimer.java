package animationTimer;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import mazeGenerator.MazeGenerator;
import mazePieces.MazeGrid;

public class MazeAnimationTimer extends AnimationTimer {
    private long currentDuration;
    private final double delaySeconds = 1; // updates the timer every second
    private final Pane root;
    private final MazeGrid mazeGrid;
    private final MazeGenerator mazeGenerator;
    private boolean initialization;

    public MazeAnimationTimer(Pane root, MazeGrid mazeGrid,
                              MazeGenerator mazeGenerator) {
        this.root = root;
        this.mazeGrid = mazeGrid;
        this.mazeGenerator = mazeGenerator;
        initialization = true;
    }

    public void run() {
        root.getChildren().removeIf(node -> node instanceof Group);

        if (initialization) {
            mazeGenerator.generateStartingPoint();
            initialization = false;
        } else {
            mazeGenerator.generatePartOfMaze();
        }

        Group cellsGroup = mazeGrid.getCellGroup();
        root.getChildren().add(cellsGroup);

        //FIXME
        System.out.println("Running...");
    }

    @Override
    public void handle(long now) {
        if (now - currentDuration >= delaySeconds * 1_000_000_000) {
            //FIXME
            System.out.println("handling...");

            run();
            currentDuration = now;
        }
    }
}
