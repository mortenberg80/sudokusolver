package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BoardTest {

        String input =
                "9,3,x,2,6,1,8,4,x," +
                        "x,x,1,x,x,9,x,6,5," +
                        "x,2,7,x,x,4,x,x,x," +
                        "x,x,2,6,4,x,3,7,x," +
                        "x,x,x,x,x,2,x,5,1," +
                        "x,5,3,x,7,x,x,x,x," +
                        "x,8,x,9,2,x,1,x,x," +
                        "x,7,9,x,1,x,x,8,x," +
                        "x,x,4,8,5,3,x,x,6";

    @Test
    void testParse() {
        Board b = Board.parse(input);
        assertThat(b.isValid()).isTrue();
        assertThat(b.get(0, 0).getValue()).isEqualTo("9");
        assertThat(b.get(0, 1).isEmpty()).isTrue();
        assertThat(b.get(0, 2).isEmpty()).isTrue();
        assertThat(b.get(0, 3).isEmpty()).isTrue();
        assertThat(b.get(0, 4).isEmpty()).isTrue();
        assertThat(b.get(0, 5).isEmpty()).isTrue();
        assertThat(b.get(0, 6).isEmpty()).isTrue();
        assertThat(b.get(0, 7).isEmpty()).isTrue();
        assertThat(b.get(0, 8).isEmpty()).isTrue();

        assertThat(b.get(1, 0).getValue()).isEqualTo("3");
        assertThat(b.get(1, 1).isEmpty()).isTrue();
        assertThat(b.get(1, 2).getValue()).isEqualTo("2");
        assertThat(b.get(1, 3).isEmpty()).isTrue();
        assertThat(b.get(1, 4).isEmpty()).isTrue();
        assertThat(b.get(1, 5).getValue()).isEqualTo("5");
        assertThat(b.get(1, 6).getValue()).isEqualTo("8");
        assertThat(b.get(1, 7).getValue()).isEqualTo("7");
        assertThat(b.get(1, 8).isEmpty()).isTrue();

        assertThat(b.get(8, 0).isEmpty()).isTrue();
        assertThat(b.get(8, 1).getValue()).isEqualTo("5");
        assertThat(b.get(8, 2).isEmpty()).isTrue();
        assertThat(b.get(8, 3).isEmpty()).isTrue();
        assertThat(b.get(8, 4).getValue()).isEqualTo("1");
        assertThat(b.get(8, 5).isEmpty()).isTrue();
        assertThat(b.get(8, 6).isEmpty()).isTrue();
        assertThat(b.get(8, 7).isEmpty()).isTrue();
        assertThat(b.get(8, 8).getValue()).isEqualTo("6");
    }

    @Test
    void testInvalidSquare() {
        String input =
                        "9,3,x,2,6,1,8,4,x," +
                        "x,x,1,x,x,9,x,6,5," +
                        "x,2,7,x,1,4,x,x,x," +
                        "x,x,2,6,4,x,3,7,x," +
                        "x,x,x,x,x,2,x,5,1," +
                        "x,5,3,x,7,x,x,x,x," +
                        "x,8,x,9,2,x,1,x,x," +
                        "x,7,9,x,1,x,x,8,x," +
                        "x,x,4,8,5,3,x,x,6";
        Board b = Board.parse(input);
        assertThat(b.isValid()).isFalse();
    }

    @Test
    void testInvalidColumn() {
        String input =
                        "9,3,x,2,6,1,8,4,x," +
                        "x,8,1,x,x,9,x,6,5," +
                        "x,2,7,x,x,4,x,x,x," +
                        "x,x,2,6,4,x,3,7,x," +
                        "x,x,x,x,x,2,x,5,1," +
                        "x,5,3,x,7,x,x,x,x," +
                        "x,8,x,9,2,x,1,x,x," +
                        "x,7,9,x,1,x,x,8,x," +
                        "x,x,4,8,5,3,x,x,6";
        Board b = Board.parse(input);
        assertThat(b.isValid()).isFalse();
    }

    @Test
    void testInvalidRow() {
        String input =
                        "9,3,x,2,6,1,8,4,x," +
                        "x,6,1,x,x,9,x,6,5," +
                        "x,2,7,x,x,4,x,x,x," +
                        "x,x,2,6,4,x,3,7,x," +
                        "x,x,x,x,x,2,x,5,1," +
                        "x,5,3,x,7,x,x,x,x," +
                        "x,8,x,9,2,x,1,x,x," +
                        "x,7,9,x,1,x,x,8,x," +
                        "x,x,4,8,5,3,x,x,6";
        Board b = Board.parse(input);
        assertThat(b.isValid()).isFalse();
    }
}
