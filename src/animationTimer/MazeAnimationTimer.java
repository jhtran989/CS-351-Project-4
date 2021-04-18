package animationTimer;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import mazeGenerator.MazeGenerator;
import mazePieces.MazeGrid;

public class MazeAnimationTimer extends AnimationTimer {
    private long currentDuration;
    private final double delaySeconds = 0.01; // updates the animation
    // according
    // to the specified delay
    private final Pane root;
    private final MazeGrid mazeGrid;
    private final MazeGenerator mazeGenerator;
    private boolean initialization;
    private boolean finalPrint;
    private CellActionSequence cellActionSequence;

    public MazeAnimationTimer(Pane root, MazeGrid mazeGrid,
                              MazeGenerator mazeGenerator,
                              CellActionSequence cellActionSequence) {
        this.root = root;
        this.mazeGrid = mazeGrid;
        this.mazeGenerator = mazeGenerator;
        this.cellActionSequence = cellActionSequence;
        initialization = true;
        finalPrint = true;
    }

    public void run() {
        //root.getChildren().removeIf(node -> node instanceof Group);

        if (initialization) {
            Group cellsGroup = mazeGrid.getCellGroup();
            root.getChildren().add(cellsGroup);
            //mazeGenerator.generateStartingPoint();
            initialization = false;
        } else {
            cellActionSequence.executeCellAction();
            //mazeGenerator.generatePartOfMaze();
        }

        if (!cellActionSequence.isComplete()) {
            //FIXME
            System.out.println("Running...");
            mazeGrid.printMazeGrid();
        }

        if (finalPrint) {
            //FIXME
            System.out.println("Final run...");
            mazeGrid.printMazeGrid();
            finalPrint = false;

            System.out.println("FINISHED!!!");
        }
    }

    @Override
    public void handle(long now) {
        if (now - currentDuration >= delaySeconds * 1_000_000_000) {
            run();
            currentDuration = now;
        }
    }
}
