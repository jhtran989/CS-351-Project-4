package utilities;

import mazePieces.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Some dummy object that holds the absolute reference to an object (can
 * extract the reference from some Cell object multiple times -- very useful
 * in the maze solvers...)
 */
public class DummyCell {
    private List<Cell> dummyList;

    public DummyCell() {
        dummyList = new ArrayList<>();
    }

    public void addToDummy(Cell cell) {
        dummyList.clear();
        dummyList.add(cell);
    }

    public Cell getFromDummy() {
        return dummyList.get(0);
    }
}
