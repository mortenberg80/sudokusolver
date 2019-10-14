package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PossibleValuesTest {

    String input =
            "x,2,3,4,5,6,7,8,9," +
            "9,x,x,x,x,x,x,x,x," +
            "8,x,x,x,x,x,x,x,x," +
            "7,x,x,x,x,x,x,x,x," +
            "6,x,x,x,x,x,x,x,x," +
            "5,x,x,x,x,x,x,x,x," +
            "4,x,x,x,x,x,x,x,x," +
            "3,x,x,x,x,x,x,x,x," +
            "2,x,x,x,x,x,x,x,x";

    Board board = Board.parse(input);

    @Test
    void rowOneMissing() {
        Row row = board.getRow(0); // "x,2,3,4,5,6,7,8,9"

        row.calculatePossibleValues();

        assertThat(row.getColumn(0).getPossibleValues()).containsExactly("1");
    }

    @Test
    void rowSeveralMissing() {
        Row row = board.getRow(1); // "9,x,x,x,x,x,x,x,x,"

        row.calculatePossibleValues();

        assertThat(row.getColumn(0).getPossibleValues()).isEmpty();
        assertThat(row.getColumn(1).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(2).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(3).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(4).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(5).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(6).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(7).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
        assertThat(row.getColumn(8).getPossibleValues()).containsExactly("1", "2", "3", "4", "5", "6", "7", "8");
    }

    @Test
    void columnOneMissing() {
        Column column = board.getColumn(0);
    
        column.calculatePossibleValues();
    
        assertThat(column.getRow(0).getPossibleValues()).containsExactly("1");
    }
    
    @Test
    void columnTwoMissing() {
        Column column = board.getColumn(1);
    
        column.calculatePossibleValues();

        assertThat(column.getRow(0).getPossibleValues()).isEmpty();
        assertThat(column.getRow(1).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(2).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(3).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(4).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(5).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(6).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(7).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
        assertThat(column.getRow(8).getPossibleValues()).containsExactly("1", "3", "4", "5", "6", "7", "8", "9");
    }
    
    @Test
    void squareOneMissing() {
        // x,2,3
        // 9,x,x
        // 8,x,x
        Square square = board.getSquare(0);

        square.calculatePossibleValues();
    
        assertThat(square.getColumn(0)[0].getPossibleValues()).containsExactly("1", "4", "5", "6", "7");
    }
}
