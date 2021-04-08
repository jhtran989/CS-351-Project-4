package mazePieces;

import javafx.scene.paint.Color;

public enum CellType {
    WALL(Color.BLACK),
    PATH_VISITED(Color.WHITE),
    PATH_UNVISITED(Color.BLUE);

    private Color color;

    CellType(Color color) {
        this.color = color;
    }
}
