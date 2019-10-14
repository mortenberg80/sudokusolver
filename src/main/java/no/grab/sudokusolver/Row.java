package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row {
    private Cell[] cells;

    public Row(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A row needs 9 cells");
        }
        this.cells = cells;
    }

    public Cell[] subCells(int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (start > end) {
            throw new IllegalArgumentException("start cannot be greater than end");
        }
        if (start == end) {
            return new Cell[]{};
        }
        return Arrays.stream(cells).filter(c -> c.getColumn() >= start && c.getColumn() < end).sorted(Comparator.comparing(Cell::getColumn)).toArray(Cell[]::new);
    }

    public Cell getColumn(int i) {
        return Arrays.stream(cells).filter(c -> c.getColumn() == i).findFirst().orElseThrow(IllegalStateException::new);
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

    public Stream<Cell> cellStream() {
        return Arrays.stream(cells);
    }

    @Override
    public String toString() {
        return Arrays.stream(cells).map(Cell::getValue).collect(Collectors.joining("-"));
    }
}
