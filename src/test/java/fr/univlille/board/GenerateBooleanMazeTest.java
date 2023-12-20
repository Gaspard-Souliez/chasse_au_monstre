// package test.java.fr.univlille.board;

// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import main.java.fr.univlille.model.board.GenerateBooleanMaze;

// public class GenerateBooleanMazeTest {
//     private boolean[][] maze;

//     @BeforeEach
//     public void init(){
//         maze = new boolean[10][10];
//     }

//     @Test
//     void testGenerateBooleanMazeMonster() {
//         GenerateBooleanMaze.generateBooleanMazeMonster(10,10);
//         assertEquals(maze.length, 10);
//         assertEquals(maze[0].length, 10);
//         int countWalls = 0;
//         for (int i = 0; i < maze.length; i++) {
//             for (int j = 0; j < maze[0].length; j++) {
//                 if (!maze[i][j]) {
//                     countWalls++;
//                 }
//             }
//         }

//         assertTrue(countWalls > 0);
//     }

//     @Test
//     void testGenerateBooleanMazeHunter() {
//         maze = GenerateBooleanMaze.generateBooleanMazeHunter(10,10);
//         assertEquals(maze.length, 10);
//         assertEquals(maze[0].length, 10);
//         for (int i = 0; i < maze.length; i++) {
//             for (int j = 0; j < maze[0].length; j++) {
//                 assertTrue(maze[i][j]);
//             }
//         }
//     }
// }