package main.java.fr.univlille.view.gameview.allview.parametersview;

import java.util.HashMap;
import java.util.Map;

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
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.AllLabyParametersView;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.ParametersView;
import main.java.fr.univlille.view.gameview.allview.View;
import main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters.ChoiceLabyParameter;
import main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters.PercentageWallParameter;
import main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters.SizeParameter;


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
public class AllLabyrintheParametersView extends View{
    private Scene s ;
    private static final String TITLE = "Paramètres du labyrinthe";

    private Map<AllLabyParametersView,VBox> map;
    private final AllLabyParametersView[] allParameters;
    private int currentParameters;
    private VBox currentV ;
    private Button buttonSuivant;
    private Button buttonPrecedent;
    private Button currenChoice;
    private Map<Integer,Button> mapButtons;
    private Parameters para;
    private HBox h;

    /**
     * Constructeur de la classe {@link ParametersView} permettant de construire l'affichage des paramètres.
     * 
     * @param para Les paramètres liés à la vue.
     */
    public AllLabyrintheParametersView(Parameters para){
        this.para=para;
        this.allParameters = new AllLabyParametersView[]{AllLabyParametersView.CHOICE_LABY_VIEW, AllLabyParametersView.SIZE_PARAMETERS,AllLabyParametersView.PERCENTAGE_WALL_VIEW};
        this.currentParameters = 0;
        this.map = new HashMap<>();
        this.mapButtons = new HashMap<>();
        this.map.put(AllLabyParametersView.CHOICE_LABY_VIEW, new ChoiceLabyParameter(para));
        this.map.put(AllLabyParametersView.SIZE_PARAMETERS, new SizeParameter(para));
        this.map.put(AllLabyParametersView.PERCENTAGE_WALL_VIEW, new PercentageWallParameter(para));
        this.currentV = this.map.get(AllLabyParametersView.CHOICE_LABY_VIEW);

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
        return AllLabyrintheParametersView.TITLE;
    }

    /**
     * Affiche les règles suivantes et active le bouton précédent si possible.
     * 
     * @return Un conteneur VBox contenant les règles suivantes.
     */
    private VBox changeParametersPlus(){
        this.buttonPrecedent.setDisable(false);
        if(this.currentParameters+2 >= this.allParameters.length) buttonSuivant.setDisable(true);
        this.currentParameters++;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentParameters);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allParameters[this.currentParameters]);
    }

    /**
     * Méthode {@code changeParameters} permettant de naviguer entre les différentes vues des paramètres, déclenchée par les boutons de navigation en bas de la page.
     * @param i
     * @return
     */
    private VBox changeParameters(int i){
        if(i==0) {
            this.buttonPrecedent.setDisable(true);
            this.buttonSuivant.setDisable(false);
        }
        else if(i==this.allParameters.length-1) {
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(true);
        }
        else{
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(false);
        }
        this.currentParameters = i;
        return this.map.get(this.allParameters[this.currentParameters]);
    }

    /**
     * Affiche les règles précédentes et active le bouton suivant si besoin.
     * 
     * @return Un conteneur VBox contenant les règles précédentes.
     */
    private VBox changeParametersMoins(){
        if(this.currentParameters+1<=this.allParameters.length) this.buttonSuivant.setDisable(false); 
        if(this.currentParameters-1 == 0) buttonPrecedent.setDisable(true);
        currentParameters--;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentParameters);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allParameters[currentParameters]);
    }

    /**
     * Méthode {@code setAllEventOnNavigableButton} permettant de définir l'ensemble des opérations à effectuer lorsqu'un bouton de navigation est activé.
     * @param bp Le {@code BorderPane} dans lequel on affiche les différentes vues des paramètres.
     */
    private void setAllEventOnNavigableButton(BorderPane bp) {
        for (Map.Entry<Integer,Button> me : mapButtons.entrySet()) {
            me.getValue().setOnAction(e-> {
                bp.setCenter(this.changeParameters(me.getKey()));
                this.currenChoice.setBorder(null);
                me.getValue().setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
                this.currenChoice = me.getValue();
                notifyObservers(AllViewEnum.PARAMETERSVIEW);
            });
            h.getChildren().addAll(me.getValue());
        }
    }

    /**
     * Méthode {@code setAllNavigableButton} permettant de définir l'ensemble des boutons servant à naviguer entre les différentes pages des paramètres.
     */
    private void setAllNavigableButton() {
        Button temp;
        for (int i = 0; i < this.allParameters.length; i++) {
            temp = new Button(""+(i+1));
            temp.setFont(OftenUse.FONT_FOR_BUTTON);
            mapButtons.put(i, temp);
            HBox.setMargin(temp, new Insets(0, 0, 20, 0));
            DropShadow dropShadow = new DropShadow();
            temp.setEffect(dropShadow);
        }
        this.currenChoice = mapButtons.get(0);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage des paramètres.
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);

        h = new HBox(OftenUse.TEXT_SIZE);

        Button buttonExit = new Button("Valider et revenir en arrière");
        this.buttonSuivant = new Button(">");
        this.buttonPrecedent = new Button("<");
        bp.setLeft(buttonPrecedent);
        bp.setRight(buttonSuivant);
        BorderPane.setAlignment(buttonPrecedent, Pos.CENTER);
        BorderPane.setAlignment(buttonSuivant, Pos.CENTER);


        
        setAllNavigableButton();
        h.getChildren().add(buttonExit);
        setAllEventOnNavigableButton(bp);
        
        
        this.buttonPrecedent.setDisable(true);
        
        buttonPrecedent.setFont(OftenUse.FONT_FOR_BUTTON);
        buttonSuivant.setFont(OftenUse.FONT_FOR_BUTTON);
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        h.setAlignment(Pos.CENTER);
        
        bp.setTop(title);
        bp.setCenter(this.currentV);
        bp.setBottom(h);

        BorderPane.setAlignment(title, Pos.CENTER);

        s = new Scene(bp);

        buttonSuivant.setOnAction(e -> {
            bp.setCenter(this.changeParametersPlus());
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        buttonPrecedent.setOnAction(e -> {
            bp.setCenter(this.changeParametersMoins());
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });

        HBox.setMargin(buttonExit, new Insets(0, 0, 20, 0));


        DropShadow dropShadow = new DropShadow();
        buttonExit.setEffect(dropShadow);
    }
    
}
