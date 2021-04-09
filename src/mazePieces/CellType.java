package mazePieces;

import javafx.scene.paint.Color;

public enum CellType {
    WALL(Color.BLACK),
    PATH(Color.WHITE),
    PATH_BACKTRACK(Color.BLUE);

    private Color color;

    CellType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        switch(this) {
            case WALL:
                return "0";
            case PATH:
                return "1";
            case PATH_BACKTRACK:
                return "2";
            default:
                return "-";
        }
    }
}
