package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SolverTest {

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

    String hardInput =
            "x,x,x,x,x,x,5,x,x," +
                    "x,7,x,5,x,3,x,x,x," +
                    "x,4,x,x,x,x,x,8,1," +
                    "x,x,x,x,6,x,x,x,7," +
                    "x,2,1,x,x,x,x,x,x," +
                    "x,x,x,9,x,x,x,x,4," +
                    "x,5,x,6,x,7,8,x,x," +
                    "x,x,x,x,3,8,x,x,x," +
                    "x,x,9,x,x,2,4,x,3";

    @Test
    void testSolve() {
        Board board = Board.parse(input);

        board.runPossibleValues();

        assertThat(board.get(2, 0).getPossibleValues()).containsExactly("5");
        assertThat(board.get(8, 0).getPossibleValues()).containsExactly("7");

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        System.out.println(board);
    }


    @Test
    void testSolveHard() {
        Board board = Board.parse(hardInput);
        System.out.println(board);

        board.runPossibleValues();

        board.solve();
        System.out.println(board);

        board.runPossibleValues();

        board.solve();
        System.out.println(board);

        board.runPossibleValues();

        board.solve();
        System.out.println(board);

        board.runPossibleValues();

        board.solve();
        System.out.println(board);

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        board.runPossibleValues();

        board.solve();

        System.out.println(board);
    }
}
