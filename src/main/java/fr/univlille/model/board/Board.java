package main.java.fr.univlille.model.board;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.utils.OftenUse;

/**
 * Classe {@code Board} permettant d'initialiser un plateau de jeu pour le monstre.<br>
 * Le tableau du monstre peut être intialisé de deux manières différentes. <br>
 * Soit par des dimentsions données, soit par un tableau de boolean donné. En effet cette classe ne crée pas les tableaux de booléens. <br>
 * Elle s'occupe majoritairement d'offrir au joueur un labyrinthe jouable en tout point. <br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 */
public class Board {

    /**
     * Position X du Monstre dans le labyrinthe.
     */
    private static int xMonstre;
    /**
     * Position Y du Monstre dans le labyrinthe.
     */
    private static int yMonstre;
    /**
     * Position X de la sortie dans le labyrinthe.
     */
    private static int xExit;
    /**
     * Position X de la sortie dans le labyrinthe.
     */
    private static int yExit;

    /**
     * Un tableau d'entier représentant les positions possibles pour le Monstre et la sortie. <br>
     * Représente les quatres coin du labyrinthe
     */
    private static int[][] postitions;
    /**
     * Tableau de booléen représentant le labyrinthe.
     */
    private static boolean[][] tab;

    /**
     * Méthode {@code initialize} permettant d'initialiser un tableau 2D représentant le terrain de jeu du monstre en fonction de la taille donnée (la hauteur et la largeur pouvant être différente). <br>
     * Ce tableau est rempli de booléen. Ici {@code false} représente les mûrs et {@code true} représente les cases vides. <br>
     * Un nouveu labyrinthe est généré tant que le labyrinthe n'est pas franchissable, c'est à dire que le Monstre ne peut pas atteindre la sortie. <br>
     * 
     * @param height L'entier représentant la hauteur souhaitée du labyrinthe.
     * @param width L'entier réprésentant la largeur souhaitée du labyrinthe.
     * @return Le tableau à double dimension de booléen. (Le labyrinthe.)
     * 
     * @see GenerateBooleanMaze
     */
   public static boolean[][] initialize(int height, int width){
        boolean[][] maze;
        do {
            maze = initialize(GenerateBooleanMaze.generateBooleanMazeMonster(height, width));
        } while (!isMazeSolvable());
        return maze;
    }

    /**
     * Méthode {@code initialise} permettant de généré la position du Monstre et de la sortie, et de garder en mémoire le tableau de booléen. <br>
     * La position du Monstre est généré aléatoirement par la méthode static {@code randomMonsterPosition()}. <br>
     * La position de la sortie est généré aléatoirement par la méthode static {@code randomExitPosition()}. <br>
     * 
     * @param height L'entier représentant la hauteur souhaitée du labyrinthe.
     * @param width L'entier réprésentant la largeur souhaitée du labyrinthe.
     * @return Le tableau à double dimension de booléen. (Le labyrinthe.)
     * 
     * @see GenerateBooleanMaze
     */
    public static boolean[][] initialize(boolean[][] tab){
        Board.tab = tab;
        GenerateBooleanMaze.setMaze(tab);
        Board.postitions = GenerateBooleanMaze.getPositions();
        Board.randomMonsterPosition();
        Board.randomExitPosition();
        return Board.tab;
    }

    

    /**
     * Méthode {@code randomMonsterPosition} positionne le monstre de manière aléatoire dans un coin du tableau donné. 
     * 
     * @see Monster
     * @see OftenUse
     */
    public static void randomMonsterPosition(){

        Random r = OftenUse.RANDOM;
        
        do {
            int i = r.nextInt(4);
            Board.xMonstre = Board.postitions[i][0];
            Board.yMonstre = Board.postitions[i][1];
                    
        } while (!tab[Board.yMonstre][Board.xMonstre]);

        Monster.setCurrentCoord(new Coordinate(xMonstre, yMonstre));
    }

     /**
     * Méthode {@code randomExitPosition} positionne la sortie de manière aléatoire dans un coin du tableau donné, 
     * puis vérifie qu'elle n'est pas au même endroit que le monstre. 
     * 
     * @see Monster
     * @see OftenUse
     */
    public static void randomExitPosition(){
        Random r = OftenUse.RANDOM;

        do {
            int i = r.nextInt(4);
            Board.xExit = Board.postitions[i][0];
            Board.yExit = Board.postitions[i][1];
        } while ((Board.xExit == Board.xMonstre && Board.yExit == Board.yMonstre) || !tab[Board.yExit][Board.xExit]);
        Monster.setExit(new Coordinate(xExit, yExit));
    }

    /**
     * Méthode {@code getxMonstre} retournant la coordonnée X du monstre.
     * 
     * @return La coordonnée X du monstre.
     */
    public static int getxMonstre(){
        return Board.xMonstre;
    }

    /**
     * Méthode {@code getyMonstre} retournant la coordonnée Y du monstre.
     * 
     * @return La coordonnée Y du monstre.
     */
    public static int getyMonstre(){
        return Board.yMonstre;
    }

    /**
     * Méthode {@code getxExit} retournant la coordonnée X de la sortie.
     * 
     * @return La coordonnée X de la sortie.
     */
    public static int getxExit(){
        return Board.xExit;
    }

    /**
     * Méthode {@code getyExit} retournant la coordonnée Y de la sortie.
     * 
     * @return La coordonnée Y de la sortie.
     */
    public static int getyExit(){
        return Board.yExit;
    }
    
    /**
     * Méthode privée {@code getValidNeighbors} permettant d'obtenir les différentes cases adjacentes à la position donnée en paramètre. <br>
     * Elle permet d'obtenir les cases adjacentes et de définir si un chemin existe ou non.
     * 
     * @param x Position X.
     * @param y Postion Y.
     * @return Un tableau à double dimension d'entier qui correspond au coordonnée x et y des cases adjacentes.
     * 
     * @see Arrays
     */
    private static int[][] getValidNeighbors(int x, int y) {
        int[][] neighbors = new int[4][2];
        int count = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1}, 
            {0, -1} 
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];


            if (nx >= 0 && ny >= 0 && nx < tab[0].length && ny < tab.length && tab[ny][nx]) {
                neighbors[count] = new int[] { nx, ny };
                count++;
            }
        }
        return Arrays.copyOf(neighbors, count);
    }

    /**
     * Méthode privée {@code isMazeSolvable} permettant de savoir si le labyrinthe est jouable, c'est à dire, si il existe un chemin 
     * entre la position initiale du Monstre et le position de la sortie.
     * 
     * @return {@code true} si le labyrinthe est jouable, {@code false} sinon.
     */
    private static boolean isMazeSolvable() {
        int startX = Board.xMonstre; 
        int startY = Board.yMonstre; 
        int endX = Board.xExit;
        int endY = Board.yExit;

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] { startX, startY });
        boolean[][] visited = new boolean[tab.length][tab[0].length];
    
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
    
            if (x == endX && y == endY) {
                return true;
            }
    
            if (visited[y][x]) {
                continue; // Skip already visited cells
            }
            visited[y][x] = true;
    
            int[][] neighbors = getValidNeighbors(x, y);
            for (int[] neighbor : neighbors) {
                int nx = neighbor[0];
                int ny = neighbor[1];
                stack.push(new int[] { nx, ny });
            }
        }
        return false;
    }

}
