package animationTimer;

import constants.CellType;

public enum CellActionType {
    UPDATE_CELL_PATH,
    UPDATE_CELL_WALL,
    UPDATE_CELL_PATH_BACKTRACK,
    UPDATE_CELL_WALL_BACKTRACK;

    public static CellActionType getCellActionTypeFromCellType(
            CellType cellType) {
        switch (cellType) {
            case CELL_WALL_PATH:
            case CELL_WALL_BACKTRACK:
            case CELL_PATH:
            case CELL_PATH_BACKTRACK:
                return cellType.getCorrespondingCellActionType();
            default:
                System.out.println();
                System.out.println("Invalid cell action type...");
                return null;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
