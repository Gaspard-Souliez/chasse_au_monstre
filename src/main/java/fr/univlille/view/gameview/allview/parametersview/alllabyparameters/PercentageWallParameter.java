package main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code KnowledgeParameter} permettant d'afficher la vue pour changer les paramètres de la taille du labyrinthe.<br>
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
public class PercentageWallParameter extends VBox{

    private Button incrementProbaButtonLittle;
    private Button decrementProbaButtonLittle;
    private Button incrementProbaButtonMid;
    private Button decrementProbaButtonMid;
    private Button incrementProbaButtonBig;
    private Button decrementProbaButtonBig;

    private TextField proba;

    private Parameters para;


    /**
     * Constructeur de la classe {@link SizeParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public PercentageWallParameter(Parameters para){
        this.para = para;
        proba = new TextField(Double.toString(this.getDisplayValueProba()));
        this.start();
    }   

    private void setNewDisplay(){
        this.proba.setText(""+this.getDisplayValueProba());
    }

    /**
     * Méthode {@code getDisplayValueWidth} permettant d'obtenir la largeur du labyrinthe actuellement affichée dans les paramètres.
     * @return La largeur du labyrinthe actuellement affichée.
     */
    private double getDisplayValueProba(){
        return this.para.getProba();
    }

    /**
     * Méthode {@code incrementHeigtAction} permettant d'incrémenter la hauteur du labyrinthe.
     */
    private void incrementProbaLittleAction(){
        this.para.incrementProbaLittle();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code decrementHeightAction} permettant de décrémenter la hauteur du labyrinthe.
     */
    private void decrementProbaLittleAction(){
        this.para.decrementProbaLittle();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code incrementWidthAction} permettant d'incrémenter la largeur du labyrinthe.
     */
    private void incrementProbaMidAction(){
        this.para.incrementProbaMid();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code decrementWidthAction} permettant de décrémenter la largeur du labyrinthe.
     */
    private void decrementProbaMidAction(){
        this.para.decrementProbaMid();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code incrementWidthAction} permettant d'incrémenter la largeur du labyrinthe.
     */
    private void incrementProbaBigAction(){
        this.para.incrementProbaBig();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code decrementWidthAction} permettant de décrémenter la largeur du labyrinthe.
     */
    private void decrementProbaBigAction(){
        this.para.decrementProbaBig();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code eventTfCol} permettant de définir les opérations à suivre lorsque le texte est changé, ce qui permet de mettre à jour les paramètres de largeur écrit au clavier.
     */
    private void eventProba() {
        double newValueProba = Double.parseDouble(proba.getText());
        if(newValueProba >= Parameters.PROBABILITY_WALL){
            proba.setText(String.valueOf(Parameters.MAX_PROBABILITY_WALL));
            this.para.setProba(Parameters.MAX_PROBABILITY_WALL);
            this.incrementProbaButtonLittle.setDisable(true);
            this.incrementProbaButtonMid.setDisable(true);
            this.incrementProbaButtonBig.setDisable(true);
            this.decrementProbaButtonLittle.setDisable(false);
            this.decrementProbaButtonMid.setDisable(false);
            this.decrementProbaButtonBig.setDisable(false);
        }
        else if (newValueProba <= Parameters.PROBABILITY_WALL){
            proba.setText(String.valueOf(Parameters.MIN_PROBABILITY_WALL));
            this.para.setProba(Parameters.MIN_PROBABILITY_WALL);
            this.incrementProbaButtonLittle.setDisable(false);
            this.incrementProbaButtonMid.setDisable(false);
            this.incrementProbaButtonBig.setDisable(false);
            this.decrementProbaButtonLittle.setDisable(true);
            this.decrementProbaButtonMid.setDisable(true);
            this.decrementProbaButtonBig.setDisable(true);
        }
        else{
            this.para.setProba(newValueProba);
            this.incrementProbaButtonLittle.setDisable(false);
            this.incrementProbaButtonMid.setDisable(false);
            this.incrementProbaButtonBig.setDisable(false);
            this.decrementProbaButtonLittle.setDisable(false);
            this.decrementProbaButtonMid.setDisable(false);
            this.decrementProbaButtonBig.setDisable(false);
        }
    }

    /**
     * Méthode {@code eventDecrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut décrémenter la largeur du labyrinthe.
     */
    private void eventDecrementProbaLittle() {
        this.decrementProbaLittleAction();
        this.eventDecrementProba();
    }

    /**
     * Méthode {@code eventIncrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut incrémenter la largeur du labyrinthe.
     */
    private void eventDecrementProbaMid() {
        this.decrementProbaMidAction();
        this.eventDecrementProba();
    }

    private void eventDecrementProbaBig() {
        this.decrementProbaBigAction();
        this.eventDecrementProba();
    }

    private void eventDecrementProba(){
        if(this.getDisplayValueProba() <= Parameters.MIN_PROBABILITY_WALL) {
            this.eventProba();
        }
    }

    /**
     * Méthode {@code eventDecrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut décrémenter la largeur du labyrinthe.
     */
    private void eventIncrementProbaLittle() {
        this.incrementProbaLittleAction();
        this.eventIncrementProba();
    }

    /**
     * Méthode {@code eventIncrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut incrémenter la largeur du labyrinthe.
     */
    private void eventIncrementProbaMid() {
        this.incrementProbaMidAction();
        this.eventIncrementProba();
    }

    private void eventIncrementProbaBig() {
        this.incrementProbaBigAction();
        this.eventIncrementProba();
    }

    private void eventIncrementProba(){
        if(this.getDisplayValueProba() >= Parameters.MAX_PROBABILITY_WALL) {
            this.eventProba();
        }
    }
    
    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de la taille du labyrinthe.<br>
     */
    public void start(){

        Label explicationLabel1 = new Label("Ici vous allez pouvoir gérer le pourcentage d'apparition des mûrs.");
        Label explicationLabel2 = new Label("Ce paramètre ne sera pris en compte seulement si vous choisissez le labyrinthe aléatoire.");
        Label explicationLabel3 = new Label("ATTENTION IL FAIT APPUIER SUR \"ENTRER\" POUR VALIDER DES NUMERO ECRIT DANS LE CHAMPS DE TEXTE");

        explicationLabel1.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        explicationLabel2.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        explicationLabel3.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        
        Label probaLabel = new Label("Probabilité d'apparition d'un mûr :");
        
        probaLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        this.incrementProbaButtonLittle = new Button("+ 0.01");
        this.decrementProbaButtonLittle = new Button("- 0.01");
        this.incrementProbaButtonMid = new Button("+ 0.05");
        this.decrementProbaButtonMid = new Button("- 0.05");
        this.incrementProbaButtonBig = new Button("+ 0.1");
        this.decrementProbaButtonBig = new Button("- 0.1");
                
        proba.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        incrementProbaButtonLittle.setDisable(true);
        incrementProbaButtonMid.setDisable(true);
        incrementProbaButtonBig.setDisable(true);
        
        this.incrementProbaButtonLittle.setOnAction(e ->eventIncrementProbaLittle());
        this.incrementProbaButtonMid.setOnAction(e ->eventIncrementProbaMid());
        this.incrementProbaButtonBig.setOnAction(e ->eventIncrementProbaBig());
        
        this.decrementProbaButtonLittle.setOnAction(e ->eventDecrementProbaLittle());
        this.decrementProbaButtonMid.setOnAction(e ->eventDecrementProbaMid());
        this.decrementProbaButtonBig.setOnAction(e ->eventDecrementProbaBig());
        
        this.proba.setOnAction(e -> eventProba());

        HBox rowsBox = new HBox(OftenUse.TEXT_SIZE, probaLabel, decrementProbaButtonLittle, decrementProbaButtonMid, decrementProbaButtonBig, proba, incrementProbaButtonBig, incrementProbaButtonMid, incrementProbaButtonLittle);

        rowsBox.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(explicationLabel1,explicationLabel2, explicationLabel3,rowsBox);
        this.setAlignment(Pos.CENTER);
    }
}
