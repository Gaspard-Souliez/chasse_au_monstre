package main.java.fr.univlille.view.gameview.allview;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import javafx.application.Platform;


/**
 * 
 * Classe {@code MenuView} permettant d'afficher en interface graphique le Menu.<br>
 * Cette classe compte comme attribut l'ensemble des pages qui lui sont filles, c'est à dire la page Jouer, la page Paramètres et la page Règles.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">javaFx</a> pour trouver toute la documentation sur javaFx.
 * 
 * @see Subject
 * @see Observer
 */
public class MenuView extends View{
    private Scene s ;
    private static final String TITLE = "MenuView";
    private PlayView playView;

    /**
     * Constructeur de la classe {@link MenuView} qui permet de construire l'affichage du Menu.<br>
     * 
     * 
     * @param playView La vue du jeu lié au Menu. 
     * @see PlayView
     * @see ParametersView
     * @see RulesView
     */
    public MenuView(PlayView playView){
        this.playView = playView;
        this.start();
    }

    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le Menu.
     * 
     * @return La {@code Scene} courante qui permettra d'afficher le Menu.
     */
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    /**
     * Méthode {@code getTitle} héritée de la classe abstraite {@link View} qui retourne le titre de la fenêtre.<br><br>
     * 
     * @return Le titre de la fenêtre.
     */
    @Override
    public String getTitle() {
        return MenuView.TITLE;
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage du Menu.
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);
        
        
        Label title = new Label("Chasseur VS Monstre");
        title.setFont(OftenUse.FONT_FOR_TITLE);
        
        Label author = View.L;
        author.setFont(OftenUse.FONT_FOR_AUTHOR);

        Button play = new Button("Jouer");
        play.setFont(OftenUse.FONT_FOR_BUTTON);

        Button rules = new Button("Lire les règles");
        rules.setFont(OftenUse.FONT_FOR_BUTTON);
        
        Button modifyParameters = new Button("Modifier les paramètres");
        modifyParameters.setFont(OftenUse.FONT_FOR_BUTTON);

        Button exit = new Button("Quitter le jeu");
        exit.setFont(OftenUse.FONT_FOR_BUTTON);

        DropShadow dropShadow = new DropShadow();
        play.setEffect(dropShadow);
        rules.setEffect(dropShadow);
        modifyParameters.setEffect(dropShadow);
        exit.setEffect(dropShadow);

        VBox v = new VBox(OftenUse.SPACE_FOR_BUTTON);
        v.setPrefSize(View.WIDTH, View.HEIGHT);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(play, modifyParameters,rules,exit);

        bp.setTop(title);
        bp.setCenter(v);
        bp.setBottom(author);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(author, Pos.CENTER);

        play.setOnAction(e -> {
            this.playView.setName();
            notifyObservers(AllViewEnum.PLAYVIEW);
        });

        modifyParameters.setOnAction(e -> notifyObservers(AllViewEnum.PARAMETERSVIEW));

        rules.setOnAction(e -> notifyObservers(AllViewEnum.RULESVIEW));
        
        exit.setOnAction(e -> Platform.exit());
        
        this.s = new Scene(bp);
    }

}
