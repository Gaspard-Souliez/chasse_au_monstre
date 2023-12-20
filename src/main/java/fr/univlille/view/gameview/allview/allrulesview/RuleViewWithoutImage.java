package main.java.fr.univlille.view.gameview.allview.allrulesview;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.java.fr.univlille.utils.OftenUse;



/**
 * 
 * Classe {@code RuleViewWithoutImage} permettant d'afficher les règles du jeu ne contenant pas d'images.<br>
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
public class RuleViewWithoutImage extends VBox{

    /**
     * Constructeur de la classe {@link RuleViewWithoutImage} qui permet de construire l'affichage des règles du jeu sans images.
     * 
     * @param rulesContent Le texte à afficher dans cette vue.
     */
    public RuleViewWithoutImage(String rulesContent){
        this.start(rulesContent);
    }   
    
    /**
     * Méthode {@code start} permettant la construction de l'affichage des règles du jeu.<br>
     * 
     * @param rulesContent Le texte à afficher dans cette vue.
     */
    public void start(String rulesContent){

        Text rulesText = new Text(rulesContent);
        rulesText.setFont(new Font(OftenUse.TEXT_SIZE));
        rulesText.setTextAlignment(TextAlignment.CENTER);
        
        this.getChildren().addAll(rulesText);
        this.setAlignment(Pos.CENTER);
    }
    
}
