package animationTimer;

import constants.CellType;

public enum CellActionType {
    UPDATE_CELL_PATH,
    UPDATE_CELL_WALL,
    UPDATE_CELL_PATH_BACKTRACK,
    UPDATE_CELL_WALL_BACKTRACK,
    INITIALIZE_START_POINT_SOLVER,
    INITIALIZE_END_POINT_SOLVER,
    UPDATE_CELL_SOLVER_TRACKER,
    UPDATE_CELL_GENERATOR_TRACKER;

    public static CellActionType getCellActionTypeFromCellType(
            CellType cellType) {
        for (CellType currentCellType : CellType.values()) {
            if (currentCellType == cellType) {
                return currentCellType.getCorrespondingCellActionType();
            }
        }

        System.out.println();
        System.out.println("Invalid cell type...");
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
