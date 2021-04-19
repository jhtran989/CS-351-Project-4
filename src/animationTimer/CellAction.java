package animationTimer;

import constants.CellType;
import mazePieces.Cell;
import mazePieces.MazeGrid;

/**
 * The class that encapsulates the "action" used to display each
 * individual change made to the visualization
 */
public class CellAction {
    private final boolean CELL_ACTION_DEBUG = true;

    private int cellRowIndex;
    private int cellColumnIndex;
    private CellActionType cellActionType;

    public CellAction(int cellRowIndex, int cellColumnIndex,
                      CellActionType cellActionType) {
        this.cellRowIndex = cellRowIndex;
        this.cellColumnIndex = cellColumnIndex;
        this.cellActionType = cellActionType;
    }

    /**
     * The "action" will be based off the current cell type (state) of the
     * initialized row and column indices, merely changing the color
     * representing that state in the animation
     *
     * @param mazeGridGUI
     */
    public void executeAction(MazeGrid mazeGridGUI) {
        Cell currentCell = mazeGridGUI.getGeneralCell(cellRowIndex,
                cellColumnIndex);

        CellType cellType =
                CellType.getCellTypeFromCellActionType(
                        cellActionType);

        if (CELL_ACTION_DEBUG) {
            System.out.println();
            System.out.println("Action, r " + cellRowIndex + " c "
                    + cellColumnIndex + " action type " + cellActionType);
        }

        if (cellType == null) {
            System.out.println();
            System.out.println("Invalid cell action...");
            return;
        }

        currentCell.setCellType(cellType);
    }
}
