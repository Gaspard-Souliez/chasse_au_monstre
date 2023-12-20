package main.java.fr.univlille.view.gameview.allview.parametersview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe {@code KnowledgeParameter} permettant d'afficher la vue pour changer les paramètres de connaissance du labyrinthe.<br>
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
public class KnowledgeParameter extends View {
    private Scene s ;
    private static final String TITLE = "Paramètres de mouvement";
    
    private Parameters para;
    private Button incrementButton;
    private Button decrementButton;
    private TextField tfChoiceValue;
    

    /**
     * Constructeur de la classe {@link KnowledgeParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public KnowledgeParameter(Parameters para){
        this.para = para;
        this.start();
    }

    @Override
    public Scene getMyScene() {
        return this.s;
    }

    @Override
    public String getTitle() {
        return KnowledgeParameter.TITLE;
    }


    /**
     * Méthode {@code eventDecrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut décrémenter la largeur du labyrinthe.
     */
    private void eventDecrementValueButton() {
        this.para.decrementValue();
        this.incrementButton.setDisable(false);
        if(this.para.getPartialKnowledgeValue() == Parameters.minMouvementPartialKnowledgeValue) this.decrementButton.setDisable(true);
        this.setNewDisplay();
    }

    /**
     * Méthode {@code eventIncrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut incrémenter la largeur du labyrinthe.
     */
    private void eventIncrementValueButton() {
        this.para.incrementValue();
        this.decrementButton.setDisable(false);
        if(this.para.getPartialKnowledgeValue() == Parameters.maxMouvementPartialKnowledgeValue) this.incrementButton.setDisable(true);
        this.setNewDisplay();
    }

    private void setNewDisplay(){
        this.tfChoiceValue.setText(""+this.para.getPartialKnowledgeValue());
    }

    /**
     * Méthode {@code eventTfRow} permettant de définir les opérations à suivre lorsque le texte est changé, ce qui permet de mettre à jour les paramètres de hauteur écrit au clavier.
     */
    private void eventTfChoice() {
        int newValue = Integer.parseInt(tfChoiceValue.getText());
        if(newValue >= Parameters.maxMouvementPartialKnowledgeValue){
            tfChoiceValue.setText(String.valueOf(Parameters.maxMouvementPartialKnowledgeValue));
            this.para.setPartialKnowledgeValue(Parameters.maxMouvementPartialKnowledgeValue);
            this.incrementButton.setDisable(true);
            this.decrementButton.setDisable(false);
        }
        else if(newValue <= Parameters.minMouvementPartialKnowledgeValue){
            tfChoiceValue.setText(String.valueOf(Parameters.minMouvementPartialKnowledgeValue));
            this.para.setPartialKnowledgeValue(Parameters.minMouvementPartialKnowledgeValue);
            this.incrementButton.setDisable(false);
            this.decrementButton.setDisable(true);
        }
        else{
            this.para.setPartialKnowledgeValue(newValue);
            this.incrementButton.setDisable(false);
            this.decrementButton.setDisable(false);
        }
    }


    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de connaissance du labyrinthe.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);
        BorderPane.setAlignment(title, Pos.CENTER);


        Label explicationKnowLabel = new Label("Ici vous pouvez choisir la connaissance du labyrinthe pour le Monstre.");
        Label choiceValueMovementPartial = new Label("Rayon d'action de la vue (nombre de case) : ");

        explicationKnowLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        choiceValueMovementPartial.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        RadioButton knowledgeComplete = new RadioButton("Connaissance complète");
        RadioButton knowledgePartial = new RadioButton("Connaissance partielle");
        tfChoiceValue = new TextField(""+this.para.getPartialKnowledgeValue());

        knowledgeComplete.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        knowledgePartial.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        knowledgeComplete.setSelected(true);

        this.incrementButton = new Button("+");
        this.decrementButton = new Button("-");

        this.incrementButton.setDisable(true);
        this.decrementButton.setDisable(true);
        this.tfChoiceValue.setDisable(true);

        
        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(choiceValueMovementPartial,this.decrementButton,tfChoiceValue,this.incrementButton);

        VBox v = new VBox(explicationKnowLabel, knowledgeComplete, knowledgePartial,h);
        v.setAlignment(Pos.CENTER);

        Button buttonExit = new Button("Valider et revenir en arrière");
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        HBox exit = new HBox(OftenUse.TEXT_SIZE);
        exit.setAlignment(Pos.CENTER);
        exit.getChildren().add(buttonExit);

        bp.setTop(title);
        bp.setCenter(v);
        bp.setBottom(exit);

        this.s = new Scene(bp);
        
        knowledgeComplete.setOnAction(e->{
            this.para.knowledgeComplete();
            knowledgeComplete.setSelected(true);
            knowledgePartial.setSelected(false);

            this.incrementButton.setDisable(true);
            this.decrementButton.setDisable(true);
            this.tfChoiceValue.setDisable(true);
        });

        knowledgePartial.setOnAction(e->{
            this.para.knowledgePartial();
            knowledgePartial.setSelected(true);
            knowledgeComplete.setSelected(false);

            if(this.para.getPartialKnowledgeValue() == Parameters.maxMouvementPartialKnowledgeValue){
                this.incrementButton.setDisable(true);
                this.decrementButton.setDisable(false);
                this.tfChoiceValue.setDisable(false);
            }
            else if(this.para.getPartialKnowledgeValue() == Parameters.minMouvementPartialKnowledgeValue){
                this.incrementButton.setDisable(false);
                this.decrementButton.setDisable(true);
                this.tfChoiceValue.setDisable(false);
            }
            else{
                this.incrementButton.setDisable(false);
                this.decrementButton.setDisable(false);
                this.tfChoiceValue.setDisable(false);
            }
        });

        this.incrementButton.setOnAction(e -> eventIncrementValueButton());

        this.decrementButton.setOnAction(e -> eventDecrementValueButton());

        this.tfChoiceValue.setOnAction(e -> eventTfChoice());

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });

        HBox.setMargin(buttonExit, new Insets(0, 0, 20, 0));

        DropShadow dropShadow = new DropShadow();
        buttonExit.setEffect(dropShadow);

    }

}
