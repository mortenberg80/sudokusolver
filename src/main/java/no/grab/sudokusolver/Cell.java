package no.grab.sudokusolver;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {
    private final static String EMPTY_RUNE = "x";
    private final static Set<String> RUNES = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    private String value;
    private final Set<String> possibleValues = new HashSet<>(RUNES);

    private Cell() {
    }

    private Cell(String value) {
        if (!RUNES.contains(value)) {
            throw new IllegalArgumentException(String.format("Unknown value for cell %s", value));
        }
        this.value = value;
        this.possibleValues.clear();
    }

    public static Cell empty() {
        return new Cell();
    }

    public static Cell of(String value) {
        Objects.requireNonNull(value);
        if (EMPTY_RUNE.equalsIgnoreCase(value)) {
            return Cell.empty();
        }
        return new Cell(value);
    }

    public String getValue() {
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
        return value.equals(cell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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

    @Override
    public String toString() {
        return "Cell{" +
                "value='" + value + '\'' +
                ", possibleValues=" + possibleValues +
                '}';
    }
}
