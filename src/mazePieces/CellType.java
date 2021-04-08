package mazePieces;

import javafx.scene.paint.Color;

public enum CellType {
    WALL(Color.BLACK),
    PATH_VISITED(Color.BLUE),
    PATH_UNVISITED(Color.WHITE);

    private Color color;

    CellType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
