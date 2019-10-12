package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SquareTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "9,3,x,2,6,1,8,4,x",
            "x,x,x,x,x,x,x,x,x",
            "1,2,3,4,5,6,7,8,9",
            "1,x,x,x,x,x,x,x,x"
    })
    void testSuccessfulParse(String input) {
        Square square = Square.parse(input);
        assertThat(square.isValid()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "9,9,x,2,6,1,8,4,x",
            "1,2,3,4,5,6,7,8,1",
            "1,x,x,x,x,x,x,x,1"
    })
    void testFailedParse(String input) {
        Square square = Square.parse(input);
        assertThat(square.isValid()).isFalse();
    }

    @Test
    void getRowOne() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getRow(0);
        assertThat(column[0].getValue()).isEqualTo("9");
        assertThat(column[1].getValue()).isEqualTo("3");
        assertThat(column[2].isEmpty()).isTrue();
    }

    @Test
    void getRowTwo() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getRow(1);
        assertThat(column[0].getValue()).isEqualTo("2");
        assertThat(column[1].getValue()).isEqualTo("6");
        assertThat(column[2].getValue()).isEqualTo("1");
    }

    @Test
    void getRowThree() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getRow(2);
        assertThat(column[0].getValue()).isEqualTo("8");
        assertThat(column[1].getValue()).isEqualTo("4");
        assertThat(column[2].isEmpty()).isTrue();
    }

    @Test
    void getColumnOne() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getColumn(0);
        assertThat(column[0].getValue()).isEqualTo("9");
        assertThat(column[1].getValue()).isEqualTo("2");
        assertThat(column[2].getValue()).isEqualTo("8");
    }

    @Test
    void getColumnTwo() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getColumn(1);
        assertThat(column[0].getValue()).isEqualTo("3");
        assertThat(column[1].getValue()).isEqualTo("6");
        assertThat(column[2].getValue()).isEqualTo("4");
    }

    @Test
    void getColumnThree() {
        Square square = Square.parse("9,3,x,2,6,1,8,4,x");
        Cell[] column = square.getColumn(2);
        assertThat(column[0].isEmpty()).isTrue();
        assertThat(column[1].getValue()).isEqualTo("1");
        assertThat(column[2].isEmpty()).isTrue();
    }
}
