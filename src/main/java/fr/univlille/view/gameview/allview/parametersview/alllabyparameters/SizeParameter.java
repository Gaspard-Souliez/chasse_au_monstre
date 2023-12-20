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
public class SizeParameter extends VBox{

    private Button incrementHeightButton;
    private Button decrementHeightButton;
    private Button incrementWidhtButton;
    private Button decrementWidhtButton;

    private TextField tfRow;
    private TextField tfCol; 

    private Parameters para;


    /**
     * Constructeur de la classe {@link SizeParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public SizeParameter(Parameters para){
        this.para = para;
        tfCol = new TextField(Integer.toString(this.getDisplayValueWidth()));
        tfRow = new TextField(Integer.toString(this.getDisplayValueHeight()));
        this.start();
    }   

    private void setNewDisplay(){
        this.tfCol.setText(""+this.getDisplayValueWidth());
        this.tfRow.setText(""+this.getDisplayValueHeight());
    }

    /**
     * Méthode {@code getDisplayValueHeight} permettant d'obtenir la hauteur du labyrinthe actuellement affichée dans les paramètres.
     * @return La hauteur du labyrinthe actuellement affichée.
     */
    private int getDisplayValueHeight(){
        return this.para.getHeight();
    }

    /**
     * Méthode {@code getDisplayValueWidth} permettant d'obtenir la largeur du labyrinthe actuellement affichée dans les paramètres.
     * @return La largeur du labyrinthe actuellement affichée.
     */
    private int getDisplayValueWidth(){
        return this.para.getWidth();
    }

    /**
     * Méthode {@code incrementHeigtAction} permettant d'incrémenter la hauteur du labyrinthe.
     */
    private void incrementHeigtAction(){
        this.para.incrementHeight();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code decrementHeightAction} permettant de décrémenter la hauteur du labyrinthe.
     */
    private void decrementHeightAction(){
        this.para.decrementHeight();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code incrementWidthAction} permettant d'incrémenter la largeur du labyrinthe.
     */
    private void incrementWidthAction(){
        this.para.incrementWidth();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code decrementWidthAction} permettant de décrémenter la largeur du labyrinthe.
     */
    private void decrementWidthAction(){
        this.para.decrementWidth();
        this.setNewDisplay();
    }

    /**
     * Méthode {@code eventTfCol} permettant de définir les opérations à suivre lorsque le texte est changé, ce qui permet de mettre à jour les paramètres de largeur écrit au clavier.
     */
    private void eventTfCol() {
        int newValueCol = Integer.parseInt(tfCol.getText());
        if(newValueCol >= Parameters.maxLengthLabyrinth[0]){
            tfCol.setText(String.valueOf(Parameters.maxLengthLabyrinth[0]));
            this.para.setColMaze(Parameters.maxLengthLabyrinth[0]);
            this.incrementWidhtButton.setDisable(true);
            this.decrementWidhtButton.setDisable(false);
        }
        else if (newValueCol <= Parameters.minLengthLabyrinth[0]){
            tfCol.setText(String.valueOf(Parameters.minLengthLabyrinth[0]));
            this.para.setColMaze(Parameters.minLengthLabyrinth[0]);
            this.incrementWidhtButton.setDisable(false);
            this.decrementWidhtButton.setDisable(true);
        }
        else{
            this.para.setColMaze(newValueCol);
            this.incrementWidhtButton.setDisable(false);
            this.decrementWidhtButton.setDisable(false);
        }
    }

    /**
     * Méthode {@code eventDecrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut décrémenter la largeur du labyrinthe.
     */
    private void eventDecrementWidhtButton() {
        this.decrementWidthAction();
        this.incrementWidhtButton.setDisable(false);
        if(this.getDisplayValueWidth() == Parameters.minLengthLabyrinth[0]) this.decrementWidhtButton.setDisable(true);
    }

    /**
     * Méthode {@code eventIncrementWidhtButton} permettant de définir les opérations à suivre lorsque l'on veut incrémenter la largeur du labyrinthe.
     */
    private void eventIncrementWidhtButton() {
        this.incrementWidthAction();
        this.decrementWidhtButton.setDisable(false);
        if(this.getDisplayValueWidth() == Parameters.maxLengthLabyrinth[0]) this.incrementWidhtButton.setDisable(true);
    }

    /**
     * Méthode {@code eventTfRow} permettant de définir les opérations à suivre lorsque le texte est changé, ce qui permet de mettre à jour les paramètres de hauteur écrit au clavier.
     */
    private void eventTfRow() {
        int newValueRow = Integer.parseInt(tfRow.getText());
        if(newValueRow >= Parameters.maxLengthLabyrinth[1]){
            tfRow.setText(String.valueOf(Parameters.maxLengthLabyrinth[1]));
            this.para.setRowMaze(Parameters.maxLengthLabyrinth[1]);
            this.incrementHeightButton.setDisable(true);
            this.decrementHeightButton.setDisable(false);
        }
        else if(newValueRow <= Parameters.minLengthLabyrinth[1]){
            tfRow.setText(String.valueOf(Parameters.minLengthLabyrinth[1]));
            this.para.setRowMaze(Parameters.minLengthLabyrinth[1]);
            this.incrementHeightButton.setDisable(false);
            this.decrementHeightButton.setDisable(true);
        }
        else{
            this.para.setRowMaze(newValueRow);
            this.incrementHeightButton.setDisable(false);
            this.decrementHeightButton.setDisable(false);
        }
    }

    /**
     * Méthode {@code eventDecrementHeightButton} permettant de définir les opérations à suivre lorsque l'on veut décrémenter la hauteur du labyrinthe.
     */
    private void eventDecrementHeightButton() {
        this.decrementHeightAction();
        this.incrementHeightButton.setDisable(false);
        if(this.getDisplayValueHeight() == Parameters.minLengthLabyrinth[1]) this.decrementHeightButton.setDisable(true);
    }

    /**
     * Méthode {@code eventIncrementHeightButton} permettant de définir les opérations à suivre lorsque l'on veut incrémenter la largeur du labyrinthe.
     */
    private void eventIncrementHeightButton() {
        this.incrementHeigtAction();
        this.decrementHeightButton.setDisable(false);
        if(this.getDisplayValueHeight() == Parameters.maxLengthLabyrinth[1]) this.incrementHeightButton.setDisable(true);
    }
    
    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de la taille du labyrinthe.<br>
     */
    public void start(){

        Label explicationLabel1 = new Label("Ici vous allez pouvoir gérer les paremètres de votre chasse au monstre.");
        Label explicationLabel2 = new Label("Vous pouvez précisément modifier la taille du labyrinthe.");
        Label explicationLabel3 = new Label("Ce paramètre ne sera pris en compte seulement si vous choisissez le labyrinthe aléatoire.");
        Label explicationLabel4 = new Label("ATTENTION IL FAIT APPUIER SUR \"ENTRER\" POUR VALIDER DES NUMERO ECRIT DANS LE CHAMPS DE TEXTE");

        explicationLabel1.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        explicationLabel2.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        explicationLabel3.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        explicationLabel4.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        
        Label colLabel = new Label("Largeur du labyrinthe :");
        Label rowsLabel = new Label("Hauteur du labyrinthe :");
        
        rowsLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        colLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        this.incrementHeightButton = new Button("+");
        this.decrementHeightButton = new Button("-");
        this.incrementWidhtButton = new Button("+");
        this.decrementWidhtButton = new Button("-");
        
        this.incrementHeightButton.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        this.decrementHeightButton.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        this.incrementWidhtButton.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        this.decrementWidhtButton.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        tfRow.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        tfCol.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        incrementHeightButton.setDisable(true);
        incrementWidhtButton.setDisable(true);
        
        this.incrementHeightButton.setOnAction(e ->eventIncrementHeightButton());
        
        this.decrementHeightButton.setOnAction(e -> eventDecrementHeightButton());
        
        this.tfRow.setOnAction(e -> eventTfRow());

        HBox rowsBox = new HBox(OftenUse.TEXT_SIZE, rowsLabel, decrementHeightButton, tfRow, incrementHeightButton);
        HBox colsBox = new HBox(OftenUse.TEXT_SIZE, colLabel, decrementWidhtButton, tfCol, incrementWidhtButton);

        rowsBox.setAlignment(Pos.CENTER);
        colsBox.setAlignment(Pos.CENTER);
        
        this.incrementWidhtButton.setOnAction(e -> eventIncrementWidhtButton());

        this.decrementWidhtButton.setOnAction(e -> eventDecrementWidhtButton());

        this.tfCol.setOnAction(e -> eventTfCol());
        
        this.getChildren().addAll(explicationLabel1,explicationLabel2,explicationLabel3, explicationLabel4,colsBox,rowsBox);
        this.setAlignment(Pos.CENTER);
    }
}
