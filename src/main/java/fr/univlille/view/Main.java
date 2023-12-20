package main.java.fr.univlille.view;


import javafx.application.Application;
import javafx.stage.Stage;
import main.java.fr.univlille.view.gameview.allview.MainView;

/**
 * 
 * Classe {@code Main} permettant d'afficher en interface graphique les différentes pages.<br>
 * Cette classe est le main, c'est donc grace à cette classe que l'interface graphique est lancé.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 */
public class Main extends Application{

	/**
     * Méthode {@code main} permettant le lancement de l'interface graphique.<br>
     */
    public static void main(String[] args) {
		launch(args);
	}

	/**
     * Méthode {@code start} permettant la construction de l'affichage des règles du jeu.
     */
	public void start(Stage primaryStage) throws Exception {
		new MainView();
	}
}
