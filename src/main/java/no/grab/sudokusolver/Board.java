package no.grab.sudokusolver;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private Square[] squares;
    private Column[] columns;
    private Row[] rows;

    public static Board parse(String input) {
        Cell[] cells = Arrays.stream(input.split(",")).map(Cell::of).toArray(Cell[]::new);
        Row row0 = new Row(Arrays.stream(cells, 0, 9).toArray(Cell[]::new));
        Row row1 = new Row(Arrays.stream(cells, 9, 18).toArray(Cell[]::new));
        Row row2 = new Row(Arrays.stream(cells, 18, 27).toArray(Cell[]::new));
        Row row3 = new Row(Arrays.stream(cells, 27, 36).toArray(Cell[]::new));
        Row row4 = new Row(Arrays.stream(cells, 36, 45).toArray(Cell[]::new));
        Row row5 = new Row(Arrays.stream(cells, 45, 54).toArray(Cell[]::new));
        Row row6 = new Row(Arrays.stream(cells, 54, 63).toArray(Cell[]::new));
        Row row7 = new Row(Arrays.stream(cells, 63, 72).toArray(Cell[]::new));
        Row row8 = new Row(Arrays.stream(cells, 72, 81).toArray(Cell[]::new));

        Row[] rows = new Row[] {
                row0,
                row1,
                row2,
                row3,
                row4,
                row5,
                row6,
                row7,
                row8
        };
        return new Board(rows);
    }

    public Board(Row[] rows) {
        if (rows.length != 9) {
            throw new IllegalArgumentException("A board needs 9 rows");
        }
        this.rows = rows;
        this.squares = toSquares(rows);
        this.columns = toColumns(squares);
    }

    public static Square[] toSquares(Row[] rows) {
        return new Square[] {
                new Square(rows[0].subCells(0, 3), rows[1].subCells(0, 3), rows[2].subCells(0, 3)),
                new Square(rows[0].subCells(3, 6), rows[1].subCells(3, 6), rows[2].subCells(3, 6)),
                new Square(rows[0].subCells(6, 9), rows[1].subCells(6, 9), rows[2].subCells(6, 9)),
                new Square(rows[3].subCells(0, 3), rows[4].subCells(0, 3), rows[5].subCells(0, 3)),
                new Square(rows[3].subCells(3, 6), rows[4].subCells(3, 6), rows[5].subCells(3, 6)),
                new Square(rows[3].subCells(6, 9), rows[4].subCells(6, 9), rows[5].subCells(6, 9)),
                new Square(rows[6].subCells(0, 3), rows[7].subCells(0, 3), rows[8].subCells(0, 3)),
                new Square(rows[6].subCells(3, 6), rows[7].subCells(3, 6), rows[8].subCells(3, 6)),
                new Square(rows[6].subCells(6, 9), rows[7].subCells(6, 9), rows[8].subCells(6, 9))
        };
    }

    public static Column[] toColumns(Square[] squares) {
        return new Column[]{
                new Column(squares[0].getColumn(0), squares[3].getColumn(0), squares[6].getColumn(0)),
                new Column(squares[0].getColumn(1), squares[3].getColumn(1), squares[6].getColumn(1)),
                new Column(squares[0].getColumn(2), squares[3].getColumn(2), squares[6].getColumn(2)),
                new Column(squares[1].getColumn(0), squares[4].getColumn(0), squares[7].getColumn(0)),
                new Column(squares[1].getColumn(1), squares[4].getColumn(1), squares[7].getColumn(1)),
                new Column(squares[1].getColumn(2), squares[4].getColumn(2), squares[7].getColumn(2)),
                new Column(squares[2].getColumn(0), squares[5].getColumn(0), squares[8].getColumn(0)),
                new Column(squares[2].getColumn(1), squares[5].getColumn(1), squares[8].getColumn(1)),
                new Column(squares[2].getColumn(2), squares[5].getColumn(2), squares[8].getColumn(2))
        };
    }

    public Board(Square[] squares) {
        if (squares.length != 9) {
            throw new IllegalArgumentException("A board needs 9 squares");
        }
        this.squares = squares;
        this.columns = toColumns(squares);

        this.rows = new Row[]{
                new Row(squares[0].getRow(0), squares[1].getRow(0), squares[2].getRow(0)),
                new Row(squares[0].getRow(1), squares[1].getRow(1), squares[2].getRow(1)),
                new Row(squares[0].getRow(2), squares[1].getRow(2), squares[2].getRow(2)),
                new Row(squares[3].getRow(0), squares[4].getRow(0), squares[5].getRow(0)),
                new Row(squares[3].getRow(1), squares[4].getRow(1), squares[5].getRow(1)),
                new Row(squares[3].getRow(2), squares[4].getRow(2), squares[5].getRow(2)),
                new Row(squares[6].getRow(0), squares[7].getRow(0), squares[8].getRow(0)),
                new Row(squares[6].getRow(1), squares[7].getRow(1), squares[8].getRow(1)),
                new Row(squares[6].getRow(2), squares[7].getRow(2), squares[8].getRow(2))
        };
    }

    public Square[] getSquares() {
        return squares;
    }

    public Column[] getColumns() {
        return columns;
    }

    public Column getColumn(int i) {
        return columns[i];
    }

    public Row[] getRows() {
        return rows;
    }

    public Row getRow(int i) {
        return rows[i];
    }

    public boolean isValid() {
        boolean validRows = Arrays.stream(rows).allMatch(Row::isValid);
        boolean validColumns = Arrays.stream(columns).allMatch(Column::isValid);
        boolean validSquares = Arrays.stream(squares).allMatch(Square::isValid);
        return validRows && validColumns && validSquares;
    }

    public Cell get(int x, int y) {
        return getColumn(x).getRow(y);
    }

    public void runPossibleValues() {
        Arrays.stream(rows).forEach(Row::calculatePossibleValues);
        Arrays.stream(columns).forEach(Column::calculatePossibleValues);
        Arrays.stream(squares).forEach(Square::calculatePossibleValues);
    }

    public Set<Cell> solvables() {
        return Arrays.stream(rows)
                .flatMap(r -> r.cellStream())
                .filter(c -> c.isSolvable())
                .collect(Collectors.toSet());
    }
}
