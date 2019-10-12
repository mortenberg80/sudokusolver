package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PossibleValuesTest {

    @Test
    void rowOneMissing() {
        Row row = Row.parse("x,2,3,4,5,6,7,8,9");

        row.calculatePossibleValues();

        assertThat(row.getColumn(0).getPossibleValues()).containsExactly("1");
    }

    @Test
    void rowTwoMissing() {
        Row row = Row.parse("x,x,3,4,5,6,7,8,9");

        row.calculatePossibleValues();

        assertThat(row.getColumn(0).getPossibleValues()).containsExactly("1", "2");
        assertThat(row.getColumn(1).getPossibleValues()).containsExactly("1", "2");
        assertThat(row.getColumn(2).getPossibleValues()).isEmpty();
    }

    @Test
    void columnOneMissing() {
        Column column = Column.parse("x,2,3,4,5,6,7,8,9");

        column.calculatePossibleValues();

        assertThat(column.getRow(0).getPossibleValues()).containsExactly("1");
    }

    @Test
    void columnTwoMissing() {
        Column column = Column.parse("x,x,3,4,5,6,7,8,9");

        column.calculatePossibleValues();

        assertThat(column.getRow(0).getPossibleValues()).containsExactly("1", "2");
        assertThat(column.getRow(1).getPossibleValues()).containsExactly("1", "2");
        assertThat(column.getRow(2).getPossibleValues()).isEmpty();
    }

    @Test
    void squareOneMissing() {
        Square square = Square.parse("x,2,3,4,5,6,7,8,9");

        square.calculatePossibleValues();

        assertThat(square.getColumn(0)[0].getPossibleValues()).containsExactly("1");
    }

    @Test
    void squareTwoMissing() {
        Square square = Square.parse("x,x,3,4,5,6,7,8,9");

        square.calculatePossibleValues();

        assertThat(square.getColumn(0)[0].getPossibleValues()).containsExactly("1", "2");
        assertThat(square.getColumn(1)[0].getPossibleValues()).containsExactly("1", "2");
        assertThat(square.getColumn(2)[0].getPossibleValues()).isEmpty();
    }

}
