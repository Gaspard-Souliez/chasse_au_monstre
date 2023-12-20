package main.java.fr.univlille.model.exception;


/**
 * 
 * Exception levé quand le {@code Monstre} (joueur) essaye de se déplacer sur une case où il n'a pas le droit.<br>
 * C'est à dire une case non vide.
 * 
 * @see Exception
 */
public class UnsupportedMove extends Exception{
    /**
     * Constructeur de l'Exception UnsupportedMove
     */
    public UnsupportedMove(){
        super();
    }
}