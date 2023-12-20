package main.java.fr.univlille.model.entity.iastrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import main.java.fr.univlille.model.cell.CellEvent;

/**
 * Classe {@code IAHunter} permettant l'implémentation de l'interface {@code IHunterStrategy} et permettant de créer une "Intelligence Artificielle" 
 * qui jouera contre le Joueur humain ou contre l'IA du Monstre. <br>
 * Cette IA est une IA aléatoire pour le moment, c'est à dire qu'elle tire dans un case aléatoire du labyrinthe.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class IAHunter implements IHunterStrategy{

    private CellEvent[][] tabHunter;
    private CellEvent[][] tab;
    private static final Random r = new Random();
    private int currentTurn;

    /**
     * Méthode {@code play()} hérité de {@code IHunterStrategy} qui permet à l'IA du Chasseur de tirer aléatoirement dans le labyrinthe. <br>
     * 
     * @return Cette méthode retourne une instance de {@code InnerIAHunterCoordinate}. Autrement dit une coordonnée.
     */
    @Override
    public ICoordinate play() {
        // int range;
        // CellEvent cell;
        // if(currentTurn == 1){
        //     cell = tab[r.nextInt(tabHunter.length)][ r.nextInt(tabHunter[0].length)];
        // } else {
        //     CellEvent center = getMaxTurn();
        //     range = currentTurn - center.getTurn();
        //     List<CellEvent> liste = aroundCell(center, range);
        //     cell = liste.get(r.nextInt(liste.size()));
        // }
        return new InnerIAHunterCoordinate(r.nextInt(tabHunter[0].length), r.nextInt(tabHunter.length));
        //return cell.getCoord();
    }

    /**
     * Méthode {@code update()} hérité de l'interface {@code IHunterStrategy} qui permet de mettre à jour 
     * les cases connues par le Chasseur.
     */
    @Override
    public void update(ICellEvent arg0) {
        ICoordinate coord = arg0.getCoord();
        CellEvent cell = new CellEvent(arg0.getState(), coord, currentTurn);
        tab[coord.getRow()][coord.getCol()] = cell;
        currentTurn++;
    }

    /**
     * Méthode {@code initialise()} hérité de l'interface {@code IHunterStrategy} permettant d'initiliser l'ensemble des éléments 
     * utilisés par l'IA pour jouer.
     * 
     * @param arg0 Ce paramètre représente le nombre de ligne que compte le labyrinthe.
     * @param arg1 Ce paramètre représente le nombre de colonne que compte le labyrinth".
     * 
     */
    @Override
    public void initialize(int arg0, int arg1) {
        currentTurn = 1;
        tab = new CellEvent[arg0][arg1];
        tabHunter = new CellEvent[arg0][arg1];
        for(int i=0; i<tabHunter.length; i++){
            for(int j=0; j<tabHunter[0].length; j++){
                tabHunter[i][j] = new CellEvent(ICellEvent.CellInfo.EMPTY, new InnerIAHunterCoordinate(i, j), 0);
            }
        }
    }

    /**
     * 
     * @return
     */
    @SuppressWarnings("unused")
    private CellEvent getMaxTurn(){
        CellEvent max = tabHunter[0][0];
        for(int i=0; i<tabHunter.length; i++){
            for(int j=0; j<tabHunter[0].length; j++){
                max = tabHunter[i][j].getTurn()>max.getTurn() ? tabHunter[i][j] : max;
            }
        }
        return max;
    }
    
    /**
     * 
     * @param c
     * @param range
     * 
     * @return
     */
    @SuppressWarnings("unused")
    private List<CellEvent> aroundCell(CellEvent c, int range){
        List<CellEvent> liste = new ArrayList<>();
        for(int i=c.getCoord().getRow()-range; i<c.getCoord().getRow()+range; i++){
            for(int j=c.getCoord().getCol()-range; j<c.getCoord().getCol()+range; j++){
                if(isValid(i,j) && i != c.getCoord().getRow() && j != c.getCoord().getCol()){
                    liste.add(tabHunter[i][j]);
                }
            }
        }
        return liste;
    }

    /**
     * Méthode {@code isValid()} permettant de définir si les entier passés en paramètre, représentant une coodonnée, sont valides. <br>
     * C'est à dire que les coordonnées ne sortent pas du labyrinthe.
     * 
     * @param x Ce paramètre représente la coordonée x à vérifiée.
     * @param y Ce paramètre représente la coordonée y à vérifiée.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si les attributs passés en paramètre sont valides ou non.
     */
    private boolean isValid(int y, int x){
        return y >= 0 && x >= 0 && y < tab.length && x < tab.length && !tab[y][x].getState().equals(ICellEvent.CellInfo.WALL);
    }

/**
 * 
 * Classe {@code InnerIAHunterCoordinate} permettant l'implémentation de linterface {@code ICoordinate}.<br>
 * Cette implémentation permet d'avoir accès au méthodes de {@code ICoordinate}. <br>
 * Cette classes est une classe interne à la classe {@code IAHunter} ce qui permet la portabilité de la classe {@code IAHunter}
 * dans le jeu des autres équipes.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
  */
public class InnerIAHunterCoordinate implements ICoordinate{


    /**
     * Valeur correspondant au nombre de ligne dans le terrain de jeu.
     */
    private int row;
    /**
     * Valeur correspondant au nombre de colonne dans le terrain de jeu.
     */
    private int col;


    /**
     * Constructeur de la classe {@link InnerIAHunterCoordinate} permettant de créer une coordonnée avec les numéros de colone et de ligne spécifiés.
     * 
     * @param col Le numéro de colone ( x ).
     * @param row Le numéro de ligne ( y ).
     */
    public InnerIAHunterCoordinate(int col, int row){
        this.col = col;
        this.row = row;
    }


    /**
     * Méthode {@code getCol} qui retourne le numéro de colonne de la coordonnée.
     * 
     * @return Le numéro de la colonne.
     */
    @Override
    public int getCol() {
        return this.col;
    }


    /**
     * Méthode {@code getRow} qui  retourne le numéro de ligne de la coordonnée.
     * 
     * @return Le numéro de la ligne.
     */
    @Override
    public int getRow() {
        return this.row;
    }

    /**
     * Méthode {@code hashCode} permettant de générer le hashCode.
     * @return Le hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }


    /**
     * Méthode {@code equals} permettant de comparer deux coordonées et de retourner
     * un booléen en fonction de si ils sont égaux ou non.
     * 
     * @param obj La deuxième coordonée pour la comparaison.
     * @return Le booléen qui définit si ils sont égaux ou non.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ICoordinate other = (ICoordinate) obj;
        if (row != other.getRow())
            return false;
        return col == other.getCol();
    }
}

}
