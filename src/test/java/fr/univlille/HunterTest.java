// package test.java.fr.univlille;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;

// import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
// import main.java.fr.univlille.model.cell.Cell;
// import main.java.fr.univlille.model.cell.CellEvent;
// import main.java.fr.univlille.model.cell.Coordinate;
// import main.java.fr.univlille.model.entity.Hunter;
// import main.java.fr.univlille.model.entity.Monster;

// public class HunterTest {
//     private Hunter hunter;
//     private int width = 5;
//     private int height = 5;
//     private Monster monstre;
//     private boolean[][] maze;

//     @BeforeEach
//     public void init() {
//         hunter = new Hunter();
//         monstre = new Monster();
//         maze = new boolean[5][5];
//         for (int i = 0; i < maze.length; i++) {
//             for (int j = 0; j < maze[i].length; j++) {
//                 maze[i][j] = true;
//             }
//         }
//         monstre.initialize(maze);
//         hunter.initialize(width, height);
//         hunter.attach(monstre);
//         monstre.attach(hunter);
//     }

//     @Test
//     public void testInitialize() {
//         assertEquals(width, hunter.getHunterBoard().length);
//         assertEquals(height, hunter.getHunterBoard()[0].length);
//     }

//     @Test
//     public void testShoot() {
//         Coordinate coord = new Coordinate(1, 1);
//         hunter.shoot(coord);
//         assertTrue(hunter.getHunterBoard()[coord.getCol()][coord.getRow()].isEmpty());
//     }

//     @Test
//     public void testUpdate() {
//         Coordinate coord = new Coordinate(0, 0);
//         Cell cell = new Cell(new CellEvent(CellInfo.EMPTY, coord, 0));
//         hunter.update(null, cell);

//         assertEquals(cell, hunter.getHunterBoard()[coord.getCol()][coord.getRow()]);
//     }

//     @Test
//     public void testInitializeEmpty(){
//         Cell[][] hunterBoard = hunter.getHunterBoard();

//         for (int i = 0; i < hunterBoard.length; i++) {
//             for (int j = 0; j < hunterBoard[i].length; j++) {
//                 assertTrue(hunterBoard[i][j].isEmpty());
//             }
//         }
//     }
// }
