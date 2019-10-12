package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row {
    private Cell[] cells;

    public static Row parse(String input) {
        Cell[] cells = Arrays.stream(input.split(",")).map(Cell::of).toArray(Cell[]::new);

        return new Row(cells);
    }

    public Row(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A row needs 9 cells");
        }
        this.cells = cells;
    }

    public Row(Cell[] cells1, Cell[] cells2, Cell[] cells3) {
        if (cells1.length != 3 || cells2.length != 3 || cells3.length != 3 ) {
            throw new IllegalArgumentException("A row needs 9 cells");
        }
        this.cells = Stream.of(cells1, cells2, cells3).flatMap(Stream::of).toArray(Cell[]::new);
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
        return Arrays.stream(cells).skip(start).limit(end - start).toArray(Cell[]::new);
    }

    public Cell getColumn(int i) {
        return cells[i];
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
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .noneMatch(e -> e.getValue() > 1);
    }

    public Stream<Cell> cellStream() {
        return Arrays.stream(cells);
    }
}
