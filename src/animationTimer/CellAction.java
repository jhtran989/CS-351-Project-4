package animationTimer;

import constants.CellType;
import mazePieces.Cell;
import mazePieces.MazeGrid;

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
