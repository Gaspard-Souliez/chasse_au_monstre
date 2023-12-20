package main.java.fr.univlille.view.gameview.allview.allrulesview;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code TurnOneTwoView} permettant d'afficher l'exemple des règles du jeu du tour 1 et 2.<br>
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
public class TurnOneTwoView extends RuleViewWithImage{

    /**
     * Constructeur de la classe {@link TurnOneTwoView} permettant de construire l'affichage de l'exemple des règles du jeu du tour 1 et 2.
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public TurnOneTwoView() throws FileNotFoundException{
        this.start();
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage de l'exemple des règles du jeu du tour 1 et 2.
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public void start() throws FileNotFoundException{

        Text[] texts = {
            createText("Table: Le chasseur interroge la case de ligne 3, colonne 5 \r\nLe monstre est informé de l’endroit où le chasseur a regardé (ici symbolisé par un t).", OftenUse.TEXT_SIZE),
            createText("Le chasseur interroge la case de ligne 5, colonne 1", OftenUse.TEXT_SIZE)
        };

        ImageView[] imageView = {
            createImageView("res/regles/photo/Tour1.png", 600, 450),
            createImageView("res/regles/photo/Tour2.png", 600, 450)
        };

        this.getChildren().addAll(imageView[0], texts[0], imageView[1], texts[1]);
        this.setAlignment(Pos.CENTER);

    }
}