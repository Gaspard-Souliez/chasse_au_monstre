package test.java.fr.univlille;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.exception.UnsupportedMove;

public class MonsterTest {
    private Monster monster = new Monster();
    private boolean[][] maze;
    private Hunter hunter = new Hunter();
    private Coordinate c;

    @Before
    public void setup() {
        maze = new boolean[10][10];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = true;
            }
        }
        this.monster.initialize(maze);
        this.hunter.initialize(10, 10);
        monster.attach(hunter);
        hunter.attach(monster);
        c = new Coordinate(1, 1);
        Monster.setCurrentCoord(c);
    }

    @Test
    public void testCreateMonstre(){
        assertTrue(c.equals(monster.getCurrentCoord()));
        assertNull(this.monster.getLastShoot());
    }

    @Test
    public void testDeplacement(){
        try {
            Coordinate c2 = new Coordinate(0, 1);
            this.monster.move(c2);
            assertTrue(c2.equals(this.monster.getCurrentCoord()));
        } catch(Exception e){
            assertTrue(false);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testNotificationTir() {
        Coordinate shootCoord = new Coordinate(1, 1);
        hunter.shoot(shootCoord);

        assertEquals(shootCoord, monster.getLastShoot());
    }

    @Test
    public void testGetCurrentCoord() {
        Coordinate currentCoord = monster.getCurrentCoord();
        assertEquals(c, currentCoord);
    }

    @Test
    public void testgetPath() {

        Coordinate coord1 = new Coordinate(1, 2);
        Coordinate coord2 = new Coordinate(2, 2);
        Coordinate coord3 = new Coordinate(3, 2);

        try {
            monster.move(coord1);
            monster.move(coord2);
            monster.move(coord3);
        } catch (UnsupportedMove e) {
            System.out.println(e.getMessage());
        }

        Map<Coordinate, Integer> path = monster.getPath();

        assertTrue(path.containsKey(coord1));
        assertTrue(path.containsKey(coord2));
        assertTrue(path.containsKey(coord3));
        assertEquals(1, (int) path.get(coord1));
        assertEquals(2, (int) path.get(coord2));
        assertEquals(3, (int) path.get(coord3));
    }

    @Test
    public void testgetLastShoot() {
        Coordinate shootCoord = new Coordinate(2, 2);
        hunter.shoot(shootCoord);

        Coordinate lastShoot = monster.getLastShoot();

        assertEquals(shootCoord, lastShoot);
    }
}
