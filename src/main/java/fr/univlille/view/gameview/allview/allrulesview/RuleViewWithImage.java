package main.java.fr.univlille.view.gameview.allview.allrulesview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * Classe {@code RuleWithImage} permettant d'afficher les règles du jeu contenant des images.<br>
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
public abstract class RuleViewWithImage extends VBox{
    
    /**
     * Créer un texte à partir d'une chaine de charactère spécifiée et la taille de police donnée.
     * 
     * @param text La chaine de caractères à transformer en un texte.
     * @param size La taille de police à appliquer.
     * @return Le texte contant la chaine de caractère avec la taille de police appliquée.
     */
    protected Text createText(String text, int size){
        Text newText = new Text(text);
        newText.setFont(new Font(size));
        return newText;
    }

    /**
     * Genère un ImageView à partir d'un chemin d'une image spécifié.
     * @param imagePath Le chemin de l'image à utiliser.
     * @return Un ImageView contenant l'image spécifiée.
     * @throws FileNotFoundException Si le fichier image spécifié n'a pas pu être trouvé.
     */
    protected ImageView createImageView(String imagePath, double maxWidth, double maxHeight) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(imagePath);
        Image img = new Image(input);
    
        ImageView imageView = new ImageView(img);
    
        // Si la largeur de l'image est supérieure à maxWidth, redimensionnez-la
        if (img.getWidth() > maxWidth) {
            imageView.setFitWidth(maxWidth);
            imageView.setPreserveRatio(true);
        }
    
        // Si la hauteur de l'image est supérieure à maxHeight, redimensionnez-la
        if (img.getHeight() > maxHeight) {
            imageView.setFitHeight(maxHeight);
            imageView.setPreserveRatio(true);
        }
    
        return imageView;
    } 

}
