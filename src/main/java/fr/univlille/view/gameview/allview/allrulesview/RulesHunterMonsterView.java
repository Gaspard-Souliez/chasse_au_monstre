package main.java.fr.univlille.view.gameview.allview.allrulesview;

import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code RulesHunterMonsterView} permettant d'afficher les règles du jeu pour Chasseur et le Monstre.<br>
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
public class RulesHunterMonsterView extends RuleViewWithImage{

    /**
     * Constructeur de la classe {@link RulesHunterMonsterView} qui permet de construire l'affichage des règles du Chasseur et du Monstre
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public RulesHunterMonsterView() throws FileNotFoundException{
        this.start();
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage des regles du chasseur et du monstre.<br>
     * 
     * @throws FileNotFoundException L'exception est levé si le fichier n'est pas trouvé.
     */
    public void start() throws FileNotFoundException{
    
        HBox h1 = new HBox(OftenUse.TEXT_SIZE*10.0);
        HBox h2 = new HBox(OftenUse.TEXT_SIZE);
        
        Text[] texts = {
            this.createText("Voici un labyrinthe, dans lequel les cases noires symbolisent les murs, le m symbolise le monstre et le s la sortie:", OftenUse.TEXT_SIZE),
            this.createText("Ce que voit le monstre :", OftenUse.TEXT_SIZE),
            this.createText("Ce que voit le chasseur :", OftenUse.TEXT_SIZE)
        };

        ImageView[] imageViews = {
            createImageView("res/regles/photo/VuMonstre.png", 400, 250),
            createImageView("res/regles/photo/VuMonstre.png", 400, 250),
            createImageView("res/regles/photo/VuChasseur.png", 400, 250)
        };
        
        h1.getChildren().addAll(texts[1], texts[2]);
        h1.setAlignment(Pos.CENTER);
       
        h2.getChildren().addAll(imageViews[1], imageViews[2]);
        h2.setAlignment(Pos.CENTER);

        this.getChildren().addAll(texts[0], imageViews[0], h1, h2);
        this.setAlignment(Pos.CENTER);
    }
    
}
