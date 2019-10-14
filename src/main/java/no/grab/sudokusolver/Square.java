package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Square {
    private Cell[] cells;

    public Square(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A square needs 9 cells");
        }
        this.cells = cells;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell[] getRow(int i) {
        switch (i) {
            case 0:
                return new Cell[]{cells[0], cells[1], cells[2]};
            case 1:
                return new Cell[]{cells[3], cells[4], cells[5]};
            case 2:
                return new Cell[]{cells[6], cells[7], cells[8]};
            default:
                throw new IllegalArgumentException("Unknown row " + i);
        }
    }

    public Cell[] getColumn(int i) {
        switch (i) {
            case 0:
                return new Cell[]{cells[0], cells[3], cells[6]};
            case 1:
                return new Cell[]{cells[1], cells[4], cells[7]};
            case 2:
                return new Cell[]{cells[2], cells[5], cells[8]};
            default:
                throw new IllegalArgumentException("Unknown column " + i);
        }
    }

    public Set<String> getValues() {
        return Arrays.stream(cells)
                .filter(Cell::hasValue)
                .map(Cell::getValue)
                .collect(Collectors.toSet());
    }

    public void calculatePossibleValues() {
        Set<String> values = getValues();
        Arrays.stream(cells).forEach(c -> c.removePossibleValues(values));
    }

    public boolean isValid() {
        return Arrays.stream(cells)
                .filter(Cell::hasValue)
                .collect(Collectors.groupingBy(Cell::getValue, Collectors.counting()))
                .entrySet()
                .stream()
                .noneMatch(e -> e.getValue() > 1);
    }
}
