package test.java.fr.univlille.cell;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;

public class CellEventTest {
    
    private CellEvent cellEvent;
    private Coordinate coord;

    @BeforeEach
    public void setup(){
        coord = new Coordinate(2, 5);
        cellEvent = new CellEvent(CellInfo.EMPTY, coord, 4);
    }

    @Test
    public void creation_d_un_evenement(){
        assertEquals(cellEvent.getState(), CellInfo.EMPTY);
        assertEquals(cellEvent.getTurn(), 4);
        assertEquals(coord, cellEvent.getCoord());
    }
}
