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
}
