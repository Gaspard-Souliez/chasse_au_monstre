package main.java.fr.univlille.view.gameview.allview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.parametersview.AllLabyrintheParametersView;
import main.java.fr.univlille.view.gameview.allview.parametersview.KnowledgeParameter;
import main.java.fr.univlille.view.gameview.allview.parametersview.MovementParameter;
import main.java.fr.univlille.view.gameview.allview.parametersview.PlayerParameter;

/**
 * 
 * Classe {@code ParametersView} permettant d'afficher en interface graphique les paramètres du jeu.<br>
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
public class ParametersView extends View implements Observer{
    private Scene s ;
    private Scene thisScene;
    private static final String TITLE = "Paramètres";

    private Parameters para;

    private AllLabyrintheParametersView labyParam;
    private KnowledgeParameter knowParam;
    private MovementParameter moveParam;
    private PlayerParameter modeParam;

    /**
     * Constructeur de la classe {@link ParametersView} permettant de construire l'affichage des paramètres.
     * 
     * @param para Les paramètres liés à la vue.
     */
    public ParametersView(Parameters para){
        this.para=para;

        this.labyParam = new AllLabyrintheParametersView(para);
        this.knowParam = new KnowledgeParameter(para);
        this.modeParam = new PlayerParameter(para);
        this.moveParam = new MovementParameter(para);
        this.labyParam.attach(this);
        this.knowParam.attach(this);
        this.modeParam.attach(this);
        this.moveParam.attach(this);


        this.start();
    }


    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché les paramètres.<br><br>
     * 
     * @return La {@code Scene} courante qui permettra d'afficher les paramètres.
     */
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    /**
     * Méthode {@code getTitle} héritée de la classe abstraite {@link View} qui retourne le titre de la fenêtre.
     * 
     * @return Le titre de la fenêtre.
     */
    @Override
    public String getTitle() {
        return ParametersView.TITLE;
    }

    @Override
    public void update(Subject subj) {
        this.s = this.thisScene;
        notifyObservers(AllViewEnum.PARAMETERSVIEW);
    }

    @Override
    public void update(Subject subj, Object data) {
        // non utilisée
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage des paramètres.
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);

        Button mode = new Button("Mode de jeu");
        mode.setFont(OftenUse.FONT_FOR_BUTTON);

        Button labyParameters = new Button("Paramètres du labyrinthe");
        labyParameters.setFont(OftenUse.FONT_FOR_BUTTON);

        Button move = new Button("Paramètres de déplacement");
        move.setFont(OftenUse.FONT_FOR_BUTTON);

        Button know = new Button("Paramètre de vue");
        know.setFont(OftenUse.FONT_FOR_BUTTON);

        mode.setOnAction(e -> {
            this.s = this.modeParam.getMyScene();
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        labyParameters.setOnAction(e -> {
            this.s = this.labyParam.getMyScene();
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        move.setOnAction(e -> {
            this.s = this.moveParam.getMyScene();
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        know.setOnAction(e -> {
            this.s = this.knowParam.getMyScene();
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });
        
        Button buttonExit = new Button("Quitter les paramètres");
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        HBox h = new HBox(OftenUse.TEXT_SIZE);
        h.setAlignment(Pos.CENTER);
        h.getChildren().add(buttonExit);

        VBox v = new VBox(OftenUse.SPACE_FOR_BUTTON);
        v.setPrefSize(View.WIDTH, View.HEIGHT-20);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(mode,labyParameters,move,know);
        
        HBox center = new HBox(v);
        center.setAlignment(Pos.CENTER);
                
        
        bp.setTop(title);
        bp.setCenter(center);
        bp.setBottom(h);

        BorderPane.setAlignment(title, Pos.CENTER);

        s = new Scene(bp);
        thisScene = s;

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });

        HBox.setMargin(buttonExit, new Insets(0, 0, 20, 0));

        DropShadow dropShadow = new DropShadow();
        mode.setEffect(dropShadow);
        labyParameters.setEffect(dropShadow);
        move.setEffect(dropShadow);
        know.setEffect(dropShadow);
        buttonExit.setEffect(dropShadow);
    }
    
}
