package main.java.fr.univlille.model.board;

/**
 * Classe {@code LabyPredef} permettant de prédéfinir 3 labyrinthes. <br>
 * Ces 3 labyrinthes peuvent être utilisée par le(s) joueur(s) à la place d'un labyrinthe généré aléatoirement.
 */
public class LabyPredef {
    
    /**
     * Méthode {@code petitLaby()} qui prédéfinir un petit labyrinthe de taille 10 x 9.
     * @return Le petit labyrinthe prédéfinit.
     */
    public static boolean[][] petitLaby(){
        return new boolean[][]{
            {true,true,false,false,false,false,false,false,true,true},
            {true ,true ,true ,true ,true ,true ,true ,true ,true ,false},
            {false,false,false,false,false,false,false,false,true ,false},
            {false,true ,true ,true ,false,true ,true ,true ,true ,false},
            {false,true ,false,true ,false,true ,false,false,true ,false},
            {false,true ,false,true ,false,true ,false,true ,true ,false},
            {false,true ,false,true ,false,true ,false,true ,false,false},
            {true,true ,false,true ,true ,true ,false,true ,true ,true },
            {true,false,false,false,false,false,false,false,false,true}
        };
    }
    
    /**
     * Méthode {@code moyenLab()} qui prédéfinir un labyrinthe moyen de taille 15 x 15.
     * @return Le labyrinthe moyen prédéfinit.
     */
    public static boolean[][] moyenLab(){
        return new boolean[][]{
            {true,true,false,false,false,false,false,false,false,false,false,false,false,true,true},
            {true ,true ,true ,true ,false,true ,true ,true ,true ,true ,false,true ,true ,true ,true},
            {false,true ,false,true ,false,true ,false,false,false,true ,false,false,false,true ,false},
            {false,true ,false,true ,false,true ,true ,true ,false,true ,true ,true ,true ,true ,false},
            {false,true ,false,true ,false,true ,false,false,false,false,false,false,false,false,false},
            {false,true ,false,true ,true ,true ,true ,true ,true ,true ,false,true ,true ,true ,false},
            {false,false,false,false,false,false,false,false,false,true ,false,true ,false,false,false},
            {false,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,false},
            {false,true ,false,false,false,false,false,false,false,false,false,true ,false,false,false},
            {false,true ,true ,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false},
            {false,true ,false,false,false,false,false,true ,false,true ,false,true ,false,false,false},
            {false,true ,true ,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,false},
            {false,false,false,true ,false,true ,false,false,false,false,false,false,false,true ,false},
            {true,true ,true ,true ,false,true ,true ,true ,true ,true ,true ,true ,true ,true ,true },
            {true,true,false,false,false,false,false,false,false,false,false,false,false,false,true}
        };
    }

    /**
     * Méthode {@code grandLab()} qui prédéfinir un grand labyrinthe de taille 30 x 20.
     * @return Le grand labyrinthe prédéfinit.
     */
    public static boolean[][] grandLab(){
        return new boolean[][]{
            {true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true },
            {true ,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,true ,true ,true ,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,true },
            {false,true ,false,true ,false,true ,false,true ,false,true ,false,false,false,false,false,false,false,true ,false,true ,false,true ,false,false,false,true ,false,true ,false},
            {false,true ,false,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,true ,true ,false,true ,false,true ,false,true ,true ,true ,true ,true ,false,true ,false},
            {false,true ,false,true ,false,false,false,true ,false,true ,false,true ,false,true ,false,true ,false,true ,false,true ,false,false,false,true ,false,false,false,true ,false},
            {false,true ,true ,true ,false,true ,false,true ,true ,true ,false,true ,false,true ,false,true ,false,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,false},
            {false,false,false,false,false,true ,false,true ,false,false,false,true ,false,false,false,true ,false,true ,false,false,false,true ,false,false,false,true ,false,true ,false},
            {false,true ,false,true ,true ,true ,false,true ,true ,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,false,true ,false},
            {false,true ,false,true ,false,false,false,false,false,false,false,true ,false,true ,false,false,false,false,false,false,false,true ,false,true ,false,false,false,true ,false},
            {false,true ,false,true ,true ,true ,true ,true ,true ,true ,false,true ,false,true ,true ,true ,true ,true ,true ,true ,true ,true ,false,true ,true ,true ,true ,true ,false},
            {false,true ,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true ,false},
            {false,true ,false,true ,true ,true ,true ,true ,true ,true ,false,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,false},
            {false,true ,false,true ,false,false,false,false,false,true ,false,true ,false,false,false,false,false,true ,false,false,false,false,false,false,false,true ,false,false,false},
            {false,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,true ,true ,false,true ,false,true ,false},
            {false,false,false,false,false,true ,false,true ,false,true ,false,false,false,true ,false,true ,false,true ,false,false,false,false,false,true ,false,true ,false,true ,false},
            {false,true ,false,true ,true ,true ,false,true ,false,true ,true ,true ,false,true ,false,true ,false,true ,false,true ,true ,true ,true ,true ,false,true ,false,true ,false},
            {false,true ,false,true ,false,false,false,false,false,false,false,true ,false,false,false,true ,false,true ,false,true ,false,true ,false,false,false,true ,false,true ,false},
            {true,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,false,true ,true ,true ,true },
            {true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true }
        };
    }
}
