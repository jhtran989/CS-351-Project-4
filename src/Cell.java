/**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class ...//TODO
 */

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell{
    private int cellSize;
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private Color color;
    private Rectangle rectangle;

    /***
     * Constructor for the Cell object.
     * @param root is the Pane we pass in to add the cell.
     * @param cellSize is the size of our cells.
     * @param color is the color of our cell.
     * @param left is for the left wall of our cell.
     * @param up is for the upper wall of our cell.
     * @param right is for the right wall of our cell.
     * @param down is for the bottom wall of our cell.
     */
    public Cell(Pane root, int cellSize, Color color, boolean left,
                boolean up, boolean right, boolean down) {
        this.color = color;
        this.cellSize = cellSize;
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        rectangle = new Rectangle(cellSize, cellSize);
        root.getChildren().add(rectangle);
    }

    /***
     * //TODO
     * GETTERS AND SETTERS FOR ALL OF OUR VALUES.
     * (will probably not be final, we will trim to the ones we use)
     * @return returns the sought after values for every get or set.
     */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCellSize() {
        return cellSize;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
