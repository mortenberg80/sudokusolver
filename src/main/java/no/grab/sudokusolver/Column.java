package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Column {
    private Cell[] cells;

    public Column(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A column needs 9 cells");
        }
        this.cells = cells;
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
                .collect(Collectors.groupingBy(Cell::getValue, Collectors.counting()))
                .entrySet()
                .stream()
                .noneMatch(e -> e.getValue() > 1);
    }

    @Override
    public String toString() {
        return Arrays.stream(cells).map(Cell::getValue).collect(Collectors.joining("-"));
    }
}
