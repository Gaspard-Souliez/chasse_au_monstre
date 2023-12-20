package main.java.fr.univlille.model.board;

import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

/**
 * Classe {@code GenerateBooleanMaze} permettant de générer un tableau de booléen pour les mûrs du labyrinthe.<br>
 * Le tableau initialisé est construit à partir d'une hauteur et d'une largeur pour créer un tableau à double dimensions de booléens.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 * 
 */
public class GenerateBooleanMaze {    

    private static int[][] postitions;
    private static boolean[][] tab;

    /**
     * Méthode {@code generateBooleanMazeMonster} permettant de générer le tableau de booléen destiné au Monstre, qui génère des murs aléatoirement, selon certaines valeurs données: la hauteur et la largeur.
     * 
     * @param height La hauteur souhaitée du labyrinthe.
     * @param width La largeur souhaitée du labyrinthe.
     * @return Le tableau de booléen destiné au Monstre.
     */
    public static boolean[][] generateBooleanMazeMonster(int height, int width){
        GenerateBooleanMaze.setMaze(new boolean[height][width]);
        GenerateBooleanMaze.setFullTrue();
        GenerateBooleanMaze.setWall();
        return tab;
    }

    public static void setMaze(boolean[][] maze){
        GenerateBooleanMaze.tab=maze;
        GenerateBooleanMaze.setPostion();
    }

    /**
     * Méthode privée {@code setPosition} permettant de définir les coordonnées x et y des quatres coins du labyrinthe afin de ne pas placer de mûrs dessus
     * et de pouvoir placer le monstre et la sortie sur deux de ces quatres coins.
     */
    private static void setPostion(){
        GenerateBooleanMaze.postitions = new int[][]{
            {tab[0].length-1, 0},
            {0,tab.length-1},
            {tab[0].length-1, tab.length-1},
            {0, 0}
        };
    }

    /**
     * Méthode {@code getPOsitions} permettant d'obtenir les coodonnées x et y des quatres coins du labyrinthes.
     * @return
     */
    public static int[][] getPositions(){
        return GenerateBooleanMaze.postitions;
    }
 
    /**
     * Méthode privée {@code setFullTrue} permettant de mettre toutes les valeurs du labyrinthe à {@code true}. <br>
     * Elle est utilisé par la méthode {@code generateBooleanMazeMonster}, elle est donc utilisée seulement pour l'initialisation du labyrinthe.
     * 
     */
    private static void setFullTrue() {
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[0].length; x++) {
                tab[y][x] = true;
            }
        }
    }

    /**
     * Méthode privée {@code setWall} permettant de définir la position des mûrs selon le pourcentage définit dans les paramètres. <br>
     * Elle est utilisé par la méthode {@code generateBooleanMazeMonster}, elle est donc utilisée seulement pour l'initialisation du labyrinthe.
     * 
     * @see Parameters
     * @see OftenUse
     */
    private static void setWall(){
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[0].length; x++) {
                if(GenerateBooleanMaze.isReservedPosition(x,y)) continue;
                tab[y][x] = OftenUse.RANDOM.nextDouble() >= Parameters.PROBABILITY_WALL;
            }
        }
    }

    /**
     * Méthode privée  {@code isReservedPosition} permettant de savoir si les coordonnées passées en paramètres sont celles d'un des quatres coins
     * du labyrinthe. <br>
     * Utilisées pour ne pas placer de mûrs dans les coins.
     * 
     * @param x Coordonées X à vérifier.
     * @param y Coordonées Y à vérifier.
     * 
     * @return {@code false} si la position n'est pas réservée, {@code false} sinon.
     */
    private static boolean isReservedPosition(int x, int y){
        for (int[] is : postitions) {
            if(is[0] == x && is[1]==y) return true;
        }
        return false;
    }
}
