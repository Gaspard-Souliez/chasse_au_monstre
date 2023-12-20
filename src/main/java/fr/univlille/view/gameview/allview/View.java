package main.java.fr.univlille.view.gameview.allview;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import main.java.fr.univlille.utils.Subject;

/**
 * 
 * Classe abstraite {@code View} aidant à l'instanciation des affichages graphiques des différentes fenêtres.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 */
public abstract class View extends Subject {
    /**
     * Attribut qui définit les auteurs du projet, il est ici pour éviter la redondance dans les différentes vues.
     */
    public static final Label L = new Label("Baptiste Bertout , Arthur Keller , Pierre Planchon , Gaspard Souliez , Mathis Decoster");

    protected static final double WIDTH = MainView.bounds.getWidth();

    protected static final double HEIGHT = MainView.bounds.getHeight()-20;

    /**
     * Méthode abstraite {@code getMyScene} qui retourne la {@code Scene} courante qui permettra d'affiché les fenêtres.<br><br>
     * 
     * @return La scène que l'on veut afficher.
     */
    public abstract Scene getMyScene();

    /**
     * Méthode {@code getTitle} qui retourne le titre de la fenêtre.
     * 
     * @return Le titre de la fenêtre que l'on veut.
     */
    public abstract String getTitle();
}