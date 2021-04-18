package mazePieces; /**
 * Author: Todd Sipe, John Tran
 * Class: CS 351
 * Project: Mazes
 * This class ...//TODO
 */

import animationTimer.CellAction;
import animationTimer.CellActionSequence;
import animationTimer.CellActionType;
import constants.CellType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Rectangle {
    public static final Cell CELL_OUT_OF_BOUNDS = new Cell();

    private double cellSize; // changed to double
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private Color color;
    private Rectangle rectangle;
    private int rowIndex;
    private int columnIndex;
    protected CellType cellType;
    private List<Cell> neighbors;
    private boolean visited;
    private Cell previousCell;
    protected int cellID;
    protected CellActionSequence cellActionSequence;

    /***
     * Constructor for the mazePieces.Cell object.
     * @param root is the Pane we pass in to add the cell.
     * @param cellSize is the size of our cells.
     * @param color is the color of our cell.
     * @param left is for the left wall of our cell.
     * @param up is for the upper wall of our cell.
     * @param right is for the right wall of our cell.
     * @param down is for the bottom wall of our cell.
     * @param rowIndex
     * @param columnIndex
     */
    public Cell(Pane root, int cellSize, Color color, boolean left,
                boolean up, boolean right, boolean down, int rowIndex,
                int columnIndex) {
        this.color = color;
        this.cellSize = cellSize;
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        rectangle = new Rectangle(cellSize, cellSize);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        root.getChildren().add(rectangle);
    }

    public Cell(double x, double y, double cellSize,
                CellType cellType, int rowIndex, int columnIndex, int cellID,
                CellActionSequence cellActionSequence) {
        super(x, y, cellSize, cellSize);
        // constructor for the rectangle

        this.cellSize = cellSize;
        this.cellType = cellType;
        setFill(cellType.getColor());

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;

        neighbors = new ArrayList<>();
        visited = false;

        this.cellID = cellID;

        this.cellActionSequence = cellActionSequence;
    }

    /**
     * Should only be used for the CELL_OUT_OF_BOUNDS constant defined above
     */
    public Cell() {
    }

    // FIXME: condense to set cell type...
    public void updateCellPath() {
        cellType = CellType.CELL_PATH;
        setFill(CellType.CELL_PATH.getColor());
    }

    public void updateCellPathBacktrack() {
        cellType = CellType.CELL_PATH_BACKTRACK;
        setFill(CellType.CELL_PATH_BACKTRACK.getColor());
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
        setFill(cellType.getColor());

        // If not one of the four specified types, then a problem occurs...
        CellActionType cellActionType =
                CellActionType.getCellActionTypeFromCellType(cellType);
        cellActionSequence.addCellAction(new CellAction(
                rowIndex, columnIndex,
                cellActionType));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;

        return this.rowIndex == cell.rowIndex
                && this.columnIndex == cell.columnIndex;
    }

    // some prime number to make pairs different...
    @Override
    public int hashCode() {
        return 31 * rowIndex + columnIndex;
    }

    @Override
    public String toString() {
        return "r - " + rowIndex + " c - " + columnIndex + " type - " + cellType;
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

    public double getCellSize() {
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

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public CellType getCellType() {
        return cellType;
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }

//    public boolean isVisited() {
//        return visited;
//    }
//
//    public void activateVisited() {
//        visited = true;
//    }

    public Cell getPreviousCell() {
        return previousCell;
    }

    public void setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
    }
}
