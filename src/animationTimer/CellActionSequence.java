package animationTimer;

import mazePieces.MazeGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that encapsulates the List of "actions" for the animation to
 * execute
 */
public class CellActionSequence {
    private MazeGrid mazeGridGUI;
    private List<CellAction> cellActionList;

    public CellActionSequence(MazeGrid mazeGridGUI) {
        this.mazeGridGUI = mazeGridGUI;
        cellActionList = new ArrayList<>();
    }

    public void addCellAction(CellAction cellAction) {
        cellActionList.add(cellAction);
    }

    public List<CellAction> getCellActionList() {
        return cellActionList;
    }

    public void executeCellAction() {
        if (!cellActionList.isEmpty()) {
            cellActionList.get(0).executeAction(mazeGridGUI);
            cellActionList.remove(0);
        }
    }

    public boolean isComplete() {
        return cellActionList.isEmpty();
    }
}
