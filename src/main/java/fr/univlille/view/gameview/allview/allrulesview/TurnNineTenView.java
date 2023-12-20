package main.java.fr.univlille.view.gameview.allview.allrulesview;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code TurnNineTenView} permettant d'afficher l'exemple des règles du jeu du tour 9 et 10.<br>
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
public class TurnNineTenView extends RuleViewWithImage{

    /**
     * Constructeur de la classe {@link TurnNineTenView} permettant de construire l'affichage de l'exemple des règles du jeu du tour 9 et 10.
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public TurnNineTenView() throws FileNotFoundException{
        this.start();
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage des règles du jeu pour le tour 9 et 10.
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public void start() throws FileNotFoundException{
        
        Text[] texts = {
            createText("Le monstre a avancé, le chasseur a interrogé différentes cases.", OftenUse.TEXT_SIZE),
            createText("Table: Le chasseur interroge la case de ligne 6, colonne 3 \r\nLe chasseur peut deviner que le monstre va à droite (au dessus il y a un mur).", OftenUse.TEXT_SIZE),
            createText("Le monstre décide d’avancer au plus vite vers la sortie, il va à droite.", OftenUse.TEXT_SIZE),
            createText("Table: Le chasseur interroge la case de ligne 6, colonne 5", OftenUse.TEXT_SIZE)
        };

        ImageView[] imageViews = {
            createImageView("res/regles/photo/Tour9.png", 600, 450),
            createImageView("res/regles/photo/Tour10.png", 600, 450)
        };

        this.getChildren().addAll(texts[0], imageViews[0], texts[1], texts[2], imageViews[1], texts[3]);
        this.setAlignment(Pos.CENTER);

    }
}
