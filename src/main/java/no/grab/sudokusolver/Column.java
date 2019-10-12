package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Column {
    private Cell[] cells;

    public static Column parse(String input) {
        Cell[] cells = Arrays.stream(input.split(",")).map(Cell::of).toArray(Cell[]::new);

        return new Column(cells);
    }

    public Column(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A column needs 9 cells");
        }
        this.cells = cells;
    }

    public Column(Cell[] cells1, Cell[] cells2, Cell[] cells3) {
        if (cells1.length != 3 || cells2.length != 3 || cells3.length != 3 ) {
            throw new IllegalArgumentException("A column needs 9 cells");
        }
        this.cells = Stream.of(cells1, cells2, cells3).flatMap(Stream::of).toArray(Cell[]::new);
    }

    public Cell getRow(int i) {
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
}
