package no.grab.sudokusolver;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {
    private final static String EMPTY_RUNE = "x";
    private final static Set<String> RUNES = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    private String value;
    private final Set<String> possibleValues = new HashSet<>(RUNES);

    private final int column;
    private final int row;

    private Cell(int column, int row) {
        this.row = row;
        this.column = column;
    }

    private Cell(String value, int column, int row) {
        if (!RUNES.contains(value)) {
            throw new IllegalArgumentException(String.format("Unknown value for cell %s", value));
        }
        this.value = value;
        this.row = row;
        this.column = column;
        this.possibleValues.clear();
    }

    public static Cell empty(int column, int row) {
        return new Cell(column, row);
    }

    public static Cell of(String value, int column, int row) {
        Objects.requireNonNull(value);
        if (EMPTY_RUNE.equalsIgnoreCase(value)) {
            return Cell.empty(column, row);
        }
        return new Cell(value, column, row);
    }

    public String getValue() {
        if (value == null) {
            return EMPTY_RUNE;
        }
        return value;
    }

    public boolean hasValue() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return value == null || value.equalsIgnoreCase("x");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Cell cell = (Cell) o;
        return column == cell.column &&
                row == cell.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void removePossibleValues(Set<String> values) {
        possibleValues.removeAll(values);
    }

    public boolean isSolvable() {
        return possibleValues.size() == 1;
    }

    public boolean solve() {
        if (isSolvable()) {
            System.out.println(String.format("Solved %s", this));
            value = possibleValues.iterator().next();
            possibleValues.remove(value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value='" + value + '\'' +
                ", possibleValues=" + possibleValues +
                ", column=" + column +
                ", row=" + row +
                '}';
    }
}
