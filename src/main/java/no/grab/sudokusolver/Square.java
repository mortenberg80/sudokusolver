package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Square {
    private Cell[] cells;

    public Square(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A square needs 9 cells");
        }
        this.cells = cells;
    }

    public Square(Cell[] cells1, Cell[] cells2, Cell[] cells3) {
        if (cells1.length != 3 || cells2.length != 3 || cells3.length != 3 ) {
            throw new IllegalArgumentException("A square needs 9 cells");
        }
        this.cells = Stream.of(cells1, cells2, cells3).flatMap(Stream::of).toArray(Cell[]::new);
    }

    public static Square parse(String csv) {
        String[] v = csv.split(",");
        return new Square(new Cell[]{
                Cell.of(v[0]), Cell.of(v[1]), Cell.of(v[2]),
                Cell.of(v[3]), Cell.of(v[4]), Cell.of(v[5]),
                Cell.of(v[6]), Cell.of(v[7]), Cell.of(v[8])
        });
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
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .noneMatch(e -> e.getValue() > 1);
    }
}
