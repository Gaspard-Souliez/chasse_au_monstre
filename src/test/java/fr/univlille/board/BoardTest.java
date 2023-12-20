// package test.java.fr.univlille.board;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
// import main.java.fr.univlille.model.cell.Cell;
// import main.java.fr.univlille.model.Game;
// import main.java.fr.univlille.model.board.Board;

// public class BoardTest {    
//     private Cell[][] hunterBoard;
//     private Cell[][] monsterBoard;


//     @BeforeEach
//     public void setUp(){
//         int numCol = 5;
//         int numRow = 5;
//         hunterBoard = Board.initialize(numRow, numCol);

//         boolean[][] maze = {
//                 {true, true, true, false, true},
//                 {true, false, false, true, true},
//                 {true, true, false, true, true},
//                 {true, false, true, false, true},
//                 {true, true, true, true, true}
//         };

//         monsterBoard = Board.initialize(maze);
//     }    
    
//     @Test
//     void test_initialize_tableau_hunter(){
//         assertEquals(5, hunterBoard.length);
//         assertEquals(5, hunterBoard[0].length);
//         for (Cell[] cells : hunterBoard) {
//             for (Cell cell : cells) {
//                 assertTrue(cell.isEmpty());
//             }
//         }
//     }
    
//     @Test
//     void test_initialize_tableau_monster(){
//         assertEquals(5, monsterBoard.length);
//         assertEquals(5, monsterBoard[0].length);
//     }
    
//     @Test
//     void test_monsterBoard_contains_monster(){
//         // On instancie Game pour avoir accès au gameTurn
//         new Game();
//         assertTrue(parcours(CellInfo.MONSTER));
//     }

//     @Test
//     void test_monsterBoard_contains_exit(){
//         assertTrue(parcours(CellInfo.EXIT));
//     }

//     @Test
//     void test_monsterBoard_contains_empty(){
//         assertTrue(parcours(CellInfo.EMPTY));
//     }

//     @Test
//     void test_monsterBoard_contains_wall(){
//         assertTrue(parcours(CellInfo.WALL));
//     }
    
//     private boolean parcours(CellInfo info){
//         for (Cell[] cells : monsterBoard) {
//             for (Cell cell : cells) {
//                 if(info.equals(CellInfo.EMPTY) && cell.isEmpty()) return true;
//                 if(info.equals(CellInfo.WALL) && cell.isWall()) return true;
//                 if(info.equals(CellInfo.MONSTER) && cell.isMonster()) return true;
//                 if(info.equals(CellInfo.EXIT) && cell.isExit()) return true;
//             }
//         }
//         return false;
//     }

// }
