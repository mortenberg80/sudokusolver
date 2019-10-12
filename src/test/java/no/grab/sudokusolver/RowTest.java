package no.grab.sudokusolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RowTest {

    private Row row = Row.parse("1,2,3,4,5,6,7,8,9");

    @Test
    void testSubCellsZero() {
        Cell[] cells = row.subCells(0, 0);
        assertThat(cells.length).isEqualTo(0);
    }

    @Test
    void testSubCellsOne() {
        Cell[] cells = row.subCells(0, 1);
        assertThat(cells.length).isEqualTo(1);
        assertThat(cells[0].getValue()).isEqualTo("1");
    }

    @Test
    void testSubCellsOneFromOne() {
        Cell[] cells = row.subCells(1, 2);
        assertThat(cells.length).isEqualTo(1);
        assertThat(cells[0].getValue()).isEqualTo("2");
    }

    @Test
    void testSubCellsTwo() {
        Cell[] cells = row.subCells(0, 2);
        assertThat(cells.length).isEqualTo(2);
        assertThat(cells[0].getValue()).isEqualTo("1");
        assertThat(cells[1].getValue()).isEqualTo("2");
    }

    @Test
    void testSubCellsNine() {
        Cell[] cells = row.subCells(0, 9);
        assertThat(cells.length).isEqualTo(9);
        assertThat(cells[0].getValue()).isEqualTo("1");
        assertThat(cells[1].getValue()).isEqualTo("2");
        assertThat(cells[2].getValue()).isEqualTo("3");
        assertThat(cells[3].getValue()).isEqualTo("4");
        assertThat(cells[4].getValue()).isEqualTo("5");
        assertThat(cells[5].getValue()).isEqualTo("6");
        assertThat(cells[6].getValue()).isEqualTo("7");
        assertThat(cells[7].getValue()).isEqualTo("8");
        assertThat(cells[8].getValue()).isEqualTo("9");
    }

    @Test
    void testSubCellsExceed() {
        Cell[] cells = row.subCells(0, 10);
        assertThat(cells.length).isEqualTo(9);
        assertThat(cells[0].getValue()).isEqualTo("1");
        assertThat(cells[1].getValue()).isEqualTo("2");
        assertThat(cells[2].getValue()).isEqualTo("3");
        assertThat(cells[3].getValue()).isEqualTo("4");
        assertThat(cells[4].getValue()).isEqualTo("5");
        assertThat(cells[5].getValue()).isEqualTo("6");
        assertThat(cells[6].getValue()).isEqualTo("7");
        assertThat(cells[7].getValue()).isEqualTo("8");
        assertThat(cells[8].getValue()).isEqualTo("9");
    }
}
