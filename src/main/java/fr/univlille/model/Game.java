package main.java.fr.univlille.model;

import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.entity.iastrategy.IAHunter;
import main.java.fr.univlille.model.entity.iastrategy.IAMonstre;
import main.java.fr.univlille.model.board.Board;
import main.java.fr.univlille.model.board.LabyPredef;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;

/**
 * 
 * Classe {@code Game} qui permet de créer le jeu.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class Game {
    private Monster monster;
    private Hunter hunter;
    /**
     * Attribut {@code currentTurn} qui permet d'avoir accès au tour courant depuis toutes les classes.
     */
    public static int currentTurn;
    public static String nameMonster;
    public static String nameHunter;

    /**
     * Constructeur de la classe {@link Game} permettant de définir un nouveau Monstre et un nouveau Chasseur.
     * 
     * @see Hunter
     * @see Monster
     */
    public Game(){
        Game.currentTurn = 1;

        if(Parameters.modeJeu.equals(AllParameters.BOTH_PLAYER)){
            this.monster = new Monster();
            this.hunter = new Hunter();
        }
        if(Parameters.modeJeu.equals(AllParameters.BOTH_AI)){
            this.monster = new Monster(new IAMonstre());
            this.hunter = new Hunter(new IAHunter());
        }
        if(Parameters.modeJeu.equals(AllParameters.HUNTER_AI_ONLY)){
            this.monster = new Monster();
            this.hunter = new Hunter(new IAHunter());
        }
        if(Parameters.modeJeu.equals(AllParameters.MONSTER_AI_ONLY)){
            this.monster = new Monster(new IAMonstre());
            this.hunter = new Hunter();
        }



        this.hunter.attach(monster);
        this.monster.attach(hunter);
        
        boolean[][] tab;
        if(Parameters.choiseLaby.equals(AllParameters.LABYRAND)){
            tab = Board.initialize(Parameters.lengthLabyrinth[1], Parameters.lengthLabyrinth[0]);
        }
        else if(Parameters.choiseLaby.equals(AllParameters.LABYPREDEF1)){
            tab = Board.initialize(LabyPredef.petitLaby());
            Parameters.lengthLabyrinth = new int[]{tab[0].length, tab.length};
        }
        else if(Parameters.choiseLaby.equals(AllParameters.LABYPREDEF2)){
            tab = Board.initialize(LabyPredef.moyenLab());
            Parameters.lengthLabyrinth = new int[]{tab[0].length, tab.length};
        }
        else{
            tab = Board.initialize(LabyPredef.grandLab());
            Parameters.lengthLabyrinth = new int[]{tab[0].length, tab.length};
        }

        this.monster.initialize(tab);
        this.hunter.initialize(tab.length,tab[0].length);
    }

    /**
     * Méthode {@code getMonster} permettant d'obtenir le Monstre du jeu.
     * @return Le Monstre.
     */
    public Monster getMonster(){
        return this.monster;
    }

    /**
     * Méthode {@code getHunter} permettant d'obtenir le Chasseur du jeu.
     * @return Le Chasseur.
     */
    public Hunter getHunter(){
        return this.hunter;
    }
}
