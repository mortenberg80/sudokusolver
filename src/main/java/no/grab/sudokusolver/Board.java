package no.grab.sudokusolver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int BOARD_SIZE = 9;

    private Set<Cell> cells;

    public static Board parse(String input) {
        Set<Cell> cells = new HashSet<>();
        String[] inputValues = input.split(",");
        for (int i = 0; i < inputValues.length; i++) {
            int row = i / BOARD_SIZE;
            int column = i % BOARD_SIZE;
            cells.add(Cell.of(inputValues[i], column, row));
        }

        return new Board(cells);
    }

    public Board(Set<Cell> cells) {
        this.cells = cells;
    }

    public Column getColumn(int i) {
        Cell[] columnCells = this.cells.stream()
                .filter(c -> c.getColumn() == i)
                .toArray(Cell[]::new);
        return new Column(columnCells);
    }

    public Row getRow(int i) {
        Cell[] rowCells = this.cells.stream()
                .filter(c -> c.getRow() == i)
                .toArray(Cell[]::new);
        return new Row(rowCells);
    }

    public Square getSquare(int i) {
        switch (i) {
            case 0:
                return getSquare(0, 0);
            case 1:
                return getSquare(1,0);
            case 2:
                return getSquare(2,0);
            case 3:
                return getSquare(0,1);
            case 4:
                return getSquare(1,1);
            case 5:
                return getSquare(2,1);
            case 6:
                return getSquare(0,2);
            case 7:
                return getSquare(1,2);
            case 8:
                return getSquare(2,2);
            default:
                throw new IllegalStateException(String.format("unknown square %d", i));
        }
    }

    public Square getSquare(int column, int row) {
        int columnFrom = column * 3; // 0 => 0, 1 => 3, 2 => 6
        int columnTo = column * 3 + 2; // 0 => 2, 1 => 5, 2 => 8
        int rowFrom = row * 3;
        int rowTo = row * 3 + 2;
        Cell[] cells = this.cells.stream()
                .filter(c -> c.getRow() >= rowFrom &&
                        c.getRow() <= rowTo &&
                        c.getColumn() >= columnFrom &&
                        c.getColumn() <= columnTo)
                .toArray(Cell[]::new);
        return new Square(cells);
    }

    public boolean isValid() {
        boolean validRows = true;
        boolean validColumns = true;
        boolean validSquares = true;
        for(int i = 0; i < BOARD_SIZE; i++) {
            validRows = validRows && getRow(i).isValid();
            validColumns = validColumns && getColumn(i).isValid();
            validSquares = validSquares && getSquare(i).isValid();
        }

        return validRows && validColumns && validSquares;
    }

    public Cell get(int x, int y) {
        return this.cells.stream()
                .filter(c -> c.getColumn() == x && c.getRow() == y)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Could not find cell at column=%s, row=%s", x, y)));
    }

    public Set<Row> getRows() {
        return IntStream.range(0, 9)
                .mapToObj(this::getRow)
                .collect(Collectors.toSet());
    }

    public Set<Column> getColumns() {
        return IntStream.range(0, 9)
                .mapToObj(this::getColumn)
                .collect(Collectors.toSet());
    }

    public Set<Square> getSquares() {
        return IntStream.range(0, 9)
                .mapToObj(this::getSquare)
                .collect(Collectors.toSet());
    }

    public void runPossibleValues() {
        getRows().forEach(Row::calculatePossibleValues);
        getColumns().forEach(Column::calculatePossibleValues);
        getSquares().forEach(Square::calculatePossibleValues);
    }

    public Set<Cell> solvables() {
        return getRows().stream()
                .flatMap(Row::cellStream)
                .filter(Cell::isSolvable)
                .collect(Collectors.toSet());
    }

    public void solve() {
        solvables().forEach(Cell::solve);
    }

    public String getPrintableRow(int i) {
        return String.format("|%s|%s|%s| |%s|%s|%s| |%s|%s|%s|\n",
                             getRow(i).getColumn(0).getValue(),
                             getRow(i).getColumn(1).getValue(),
                             getRow(i).getColumn(2).getValue(),
                             getRow(i).getColumn(3).getValue(),
                             getRow(i).getColumn(4).getValue(),
                             getRow(i).getColumn(5).getValue(),
                             getRow(i).getColumn(6).getValue(),
                             getRow(i).getColumn(7).getValue(),
                             getRow(i).getColumn(8).getValue());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String spacer = "+-+-+-+ +-+-+-+ +-+-+-+\n";
        builder.append(spacer);
        builder.append(getPrintableRow(0));
        builder.append(getPrintableRow(1));
        builder.append(getPrintableRow(2));
        builder.append(spacer);
        builder.append(getPrintableRow(3));
        builder.append(getPrintableRow(4));
        builder.append(getPrintableRow(5));
        builder.append(spacer);
        builder.append(getPrintableRow(6));
        builder.append(getPrintableRow(7));
        builder.append(getPrintableRow(8));
        builder.append(spacer);
        return builder.toString();
    }
}
