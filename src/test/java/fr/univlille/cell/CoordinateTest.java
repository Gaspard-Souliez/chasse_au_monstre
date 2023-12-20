package test.java.fr.univlille.cell;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.model.cell.Coordinate;

public class CoordinateTest {
    Coordinate coor = new Coordinate(3, 4);
    
    @Test
    void test_methode_getCol() {
        assertEquals(3, coor.getCol());
    }

    @Test
    void test_methode_getRow() {
        assertEquals(4, coor.getRow());
    }

    @Test
    void test_methode_CompareCol() {
        assertEquals(3, coor.compareCol(6));
    }

    @Test
    void test_methode_CompareRow() {
        assertEquals(2, coor.compareRow(6));
    }
}
