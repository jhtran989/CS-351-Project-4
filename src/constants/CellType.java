package constants;

import animationTimer.CellActionType;
import javafx.scene.paint.Color;

public enum CellType {
    CELL_WALL(Color.BLACK, null),
    CELL_WALL_PATH(Color.WHITE,
            CellActionType.UPDATE_CELL_WALL),
    CELL_WALL_BACKTRACK(Color.BLUE,
            CellActionType.UPDATE_CELL_WALL_BACKTRACK),
    CELL_PATH_EMPTY(Color.BLACK, null),
    CELL_PATH(Color.WHITE,
            CellActionType.UPDATE_CELL_PATH),
    CELL_PATH_BACKTRACK(Color.BLUE,
            CellActionType.UPDATE_CELL_PATH_BACKTRACK);

    private Color color;
    private CellActionType correspondingCellActionType;

    CellType(Color color, CellActionType correspondingCellActionType) {
        this.color = color;
        this.correspondingCellActionType = correspondingCellActionType;
    }

    public Color getColor() {
        return color;
    }

    public CellActionType getCorrespondingCellActionType() {
        return correspondingCellActionType;
    }

    public static CellType getCellTypeFromCellActionType(
            CellActionType cellActionType) {
        for (CellType cellType : values()) {
            if (cellType.getCorrespondingCellActionType() == cellActionType) {
                return cellType;
            }
        }

        System.out.println();
        System.out.println("Invalid cell action type...");
        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case CELL_WALL:
            case CELL_PATH_EMPTY:
                return "0";
            case CELL_WALL_PATH:
            case CELL_PATH:
                return "1";
            case CELL_WALL_BACKTRACK:
            case CELL_PATH_BACKTRACK:
                return "2";
            default:
                return "-";
        }
    }
}
