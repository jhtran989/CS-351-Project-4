package animationTimer;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import mazeGenerator.MazeGenerator;
import mazePieces.MazeGrid;

public class MazeAnimationTimer extends AnimationTimer {
    private long currentDuration;
    private final double delaySeconds = 0.25; // updates the timer every second
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
        //root.getChildren().removeIf(node -> node instanceof Group);

        Group cellsGroup = mazeGrid.getCellGroup();

        if (initialization) {
            root.getChildren().add(cellsGroup);
            mazeGenerator.generateStartingPoint();
            initialization = false;
        } else {
            mazeGenerator.generatePartOfMaze();
        }

        //FIXME
        System.out.println("Running...");
        mazeGrid.printMazeGrid();
    }

    @Override
    public void handle(long now) {
        if (now - currentDuration >= delaySeconds * 1_000_000_000) {
            run();
            currentDuration = now;
        }
    }
}
