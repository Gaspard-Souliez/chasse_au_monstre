package main.java.fr.univlille.view.gameview.allview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.AllRules;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.allrulesview.RuleViewWithoutImage;
import main.java.fr.univlille.view.gameview.allview.allrulesview.RulesHunterMonsterView;
import main.java.fr.univlille.view.gameview.allview.allrulesview.TurnNineTenView;
import main.java.fr.univlille.view.gameview.allview.allrulesview.TurnOneTwoView;



/**
 * 
 * Classe {@code RulesView} permettant d'afficher en interface graphique les règles du jeu.<br>
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
public class RulesView extends View{
    private static final String IMAGE_NOT_FOUND = "Les images n'ont pas pu être chargées";
    private Scene s ;
    private static final String TITLE = "Règles";  
    private Map<AllRules,VBox> map;
    private final AllRules[] allRules;
    private int currentRules;
    private StringBuilder sc;
    private VBox currentV ;
    private Button buttonSuivant;
    private Button buttonPrecedent;
    private Button currenChoice;
    private Map<Integer,Button> mapButtons;
    private HBox h;

    private static final String PATH_GENERAL_RULES = "res/regles/RegleBut.txt";
    private static final String PATH_UNDERSTANDING_RULES = "res/regles/Connaissances.txt";
    private static final String PATH_EXPLAIN_TURN = "res/regles/Tours.txt";

    /**
     * Constructeur de la classe {@link RulesView} permettant de construire l'affichage des règles du jeu.
     */
    public RulesView(){
        this.allRules = new AllRules[]{AllRules.GENERALRULES, AllRules.UNDERSTANDINGVIEW, AllRules.EXPLAINTURN, AllRules.RULESHUNTERMONSTERVIEW, AllRules.TURNONETWOVIEW, AllRules.TURNNINETENVIEW};
        this.currentRules = 0;
        this.sc = new StringBuilder();
        this.mapButtons = new HashMap<>();
        this.setMap();
        this.currentV = this.map.get(AllRules.GENERALRULES);
        this.start();
    }

    private void setMap(){
        this.map = new HashMap<>();
        this.map.put(AllRules.GENERALRULES, new RuleViewWithoutImage(readRules(RulesView.PATH_GENERAL_RULES)));
        this.map.put(AllRules.UNDERSTANDINGVIEW, new RuleViewWithoutImage(readRules(RulesView.PATH_UNDERSTANDING_RULES)));
        this.map.put(AllRules.EXPLAINTURN, new RuleViewWithoutImage(readRules(RulesView.PATH_EXPLAIN_TURN)));
        try {
            this.map.put(AllRules.RULESHUNTERMONSTERVIEW, new RulesHunterMonsterView());
        } catch (FileNotFoundException e) {
            this.map.put(AllRules.RULESHUNTERMONSTERVIEW, new RuleViewWithoutImage(IMAGE_NOT_FOUND));
        }

        try {
            this.map.put(AllRules.TURNONETWOVIEW, new TurnOneTwoView());
        } catch (FileNotFoundException e) {
            this.map.put(AllRules.TURNONETWOVIEW, new RuleViewWithoutImage(IMAGE_NOT_FOUND));
        }

        try {
            this.map.put(AllRules.TURNNINETENVIEW, new TurnNineTenView());
        } catch (FileNotFoundException e) {
            this.map.put(AllRules.TURNNINETENVIEW, new RuleViewWithoutImage(IMAGE_NOT_FOUND));
        }
    }

    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le jeu.<br><br>
     * 
     * @return La {@code Scene} courante qui permettra d'afficher les règles.
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
        return RulesView.TITLE;
    }

    /**
     * Lit le contenu d'un fichier texte situé à l'emplacement spécifié et le retourne sous forme de chaîne de caractères.
     * 
     * @param filePath Le chemin du fichier texte à lire.
     * @return Le contenu du fichier texte sous forme de chaîne de caractères.
     */
    private String readRulesFromFile(String filePath) throws IOException{
        sc.delete(0, sc.length());
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                this.sc.append(ligne).append("\n");
            }
        }
        return this.sc.toString();
    }

    /**
     * Lit le contenu d'un fichier de règles situé à l'emplacement spécifié.
     * 
     * @param path Le chemin du fichier de règles à lire
     * @return Le contenu du fichier sous la forme d'une chaine de caractères ou une erreur en cas d'échec.
     */ 
    private String readRules(String path){
        try{
            return readRulesFromFile(path);
        }
        catch(IOException e){return "Erreur lors de la lecture des règles";}
    }

    /**
     * Affiche les règles suivantes et active le bouton précédent si possible.
     * 
     * @return Un conteneur VBox contenant les règles suivantes.
     */
    private VBox changeRulesPlus(){
        this.buttonPrecedent.setDisable(false);
        if(this.currentRules+2 >= this.allRules.length) buttonSuivant.setDisable(true);
        this.currentRules++;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentRules);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allRules[this.currentRules]);
    }

    private VBox changerRules(int i){
        if(i==0) {
            this.buttonPrecedent.setDisable(true);
            this.buttonSuivant.setDisable(false);
        }
        else if(i==this.allRules.length-1) {
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(true);
        }
        else{
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(false);
        }
        this.currentRules = i;
        return this.map.get(this.allRules[this.currentRules]);
    }

    /**
     * Affiche les règles précédentes et active le bouton suivant si besoin.
     * 
     * @return Un conteneur VBox contenant les règles précédentes.
     */
    private VBox changeRulesMoins(){
        if(this.currentRules+1<=this.allRules.length) this.buttonSuivant.setDisable(false); 
        if(this.currentRules-1 == 0) buttonPrecedent.setDisable(true);
        currentRules--;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentRules);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allRules[currentRules]);
    }

    /**
     * Méthode {@code setAllEventOnNavigableButton} permettant de définir l'ensemble des opérations à effectuer lorsqu'un bouton de navigation est activé.
     * @param bp Le {@code BorderPane} dans lequel on affiche les différentes vues des règles.
     */
    private void setAllEventOnNavigableButton(BorderPane bp) {
        for (Map.Entry<Integer,Button> me : mapButtons.entrySet()) {
            me.getValue().setOnAction(e-> {
                bp.setCenter(this.changerRules(me.getKey()));
                this.currenChoice.setBorder(null);
                me.getValue().setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
                this.currenChoice = me.getValue();
                notifyObservers(AllViewEnum.RULESVIEW);
            });
            h.getChildren().addAll(me.getValue());
        }
    }

    /**
     * Méthode {@code setAllNavigableButton} permettant de définir l'ensemble des boutons servant à naviguer entre les différentes pages des règles.
     */
    private void setAllNavigableButton() {
        Button temp;
        for (int i = 0; i < this.allRules.length; i++) {
            temp = new Button(""+(i+1));
            temp.setFont(OftenUse.FONT_FOR_BUTTON);
            mapButtons.put(i, temp);

        }
        this.currenChoice = mapButtons.get(0);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
    }
    

    /**
     * Méthode {@code start} permettant la construction de l'affichage des règles du jeu.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);


        this.h = new HBox(OftenUse.TEXT_SIZE);

        Label title = new Label("Les règles du jeu");
        title.setFont(OftenUse.FONT_FOR_TITLE);

        Button buttonExit = new Button("Quitter les règles");
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);

        this.buttonSuivant = new Button(">");
        buttonSuivant.setFont(OftenUse.FONT_FOR_BUTTON);

        this.buttonPrecedent = new Button("<");
        buttonPrecedent.setFont(OftenUse.FONT_FOR_BUTTON);
        this.buttonPrecedent.setDisable(true);


        bp.setLeft(buttonPrecedent);
        bp.setRight(buttonSuivant);
        BorderPane.setAlignment(buttonPrecedent, Pos.CENTER);
        BorderPane.setAlignment(buttonSuivant, Pos.CENTER);


        
        setAllNavigableButton();
        h.getChildren().add(buttonExit);
        setAllEventOnNavigableButton(bp);
        
        
        
        
        h.setAlignment(Pos.CENTER);
        
        bp.setTop(title);
        bp.setCenter(this.currentV);
        bp.setBottom(h);

        BorderPane.setAlignment(title, Pos.CENTER);

        s = new Scene(bp);

        buttonSuivant.setOnAction(e -> {
            bp.setCenter(this.changeRulesPlus());
            notifyObservers(AllViewEnum.RULESVIEW);
        });

        buttonPrecedent.setOnAction(e -> {
            bp.setCenter(this.changeRulesMoins());
            notifyObservers(AllViewEnum.RULESVIEW);
        });

        buttonExit.setOnAction(e -> notifyObservers());
    }
    
}
