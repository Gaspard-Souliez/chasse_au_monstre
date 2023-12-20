package main.java.fr.univlille.model.cell;

import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
 * 
 * Classe {@code Coordinate} permettant l'implémentation de linterface {@code ICoordinate}.<br>
 * Cette implémentation permet d'avoir accès au méthodes de {@code ICoordinate}.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 */
public class Coordinate implements ICoordinate{


    /**
     * Valeur correspondant au nombre de ligne dans le terrain de jeu.
     */
    private int row;
    /**
     * Valeur correspondant au nombre de colonne dans le terrain de jeu.
     */
    private int col;


    /**
     * Constructeur de la classe {@link Coordinate} permettant de créer une coordonnée avec les numéros de colone et de ligne spécifiés.
     * 
     * @param col Le numéro de colone ( x ).
     * @param row Le numéro de ligne ( y ).
     */
    public Coordinate(int col, int row){
        this.col = col;
        this.row = row;
    }


    /**
     * Méthode {@code getCol} qui retourne le numéro de colonne de la coordonnée.
     * 
     * @return Le numéro de la colonne.
     * 
     * @see ICoordinate
     */
    @Override
    public int getCol() {
        return this.col;
    }


    /**
     * Méthode {@code getRow} qui  retourne le numéro de ligne de la coordonnée.
     * 
     * @return Le numéro de la ligne.
     * 
     * @see ICoordinate
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
