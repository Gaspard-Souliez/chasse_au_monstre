package main.java.fr.univlille.view.gameview.allview.parametersview;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe {@code KnowledgeParameter} permettant d'afficher la vue pour changer les paramètres de mouvements dans le labyrinthe.<br>
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
public class MovementParameter extends View{
    private Scene s ;
    private static final String TITLE = "Paramètres de déplacement";


    private Parameters para;

    /**
     * Constructeur de la classe {@link MovementParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public MovementParameter(Parameters para){
        this.para=para;
        this.start();
    }   

    @Override
    public Scene getMyScene() {
        return this.s;
    }

    @Override
    public String getTitle() {
        return MovementParameter.TITLE;
    }

    
    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de mouvements dans le labyrinthe.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);
        BorderPane.setAlignment(title, Pos.CENTER);

        Label explcationMouvLabel = new Label("Ici vous pouvez choisir le type de déplacement du monstre.");

        explcationMouvLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        RadioButton mouvementEightWithoutPassingWall = new RadioButton("Mouvement en 8 (impossible de passer les mûrs)");
        RadioButton mouvementEightWithPassingWall = new RadioButton("Mouvement en 8 (possible de passer les mûrs)");
        RadioButton mouvementFour = new RadioButton("Mouvement en 4");

        mouvementEightWithoutPassingWall.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mouvementEightWithPassingWall.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mouvementFour.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        mouvementEightWithoutPassingWall.setSelected(true);

        mouvementEightWithoutPassingWall.setOnAction(e->{
            this.para.mouvementEightWithoutPassingWall();
            mouvementEightWithoutPassingWall.setSelected(true);
            mouvementEightWithPassingWall.setSelected(false);
            mouvementFour.setSelected(false);
        });

        mouvementEightWithPassingWall.setOnAction(e->{
            this.para.mouvementEightWithPassingWall();
            mouvementEightWithPassingWall.setSelected(true);
            mouvementEightWithoutPassingWall.setSelected(false);
            mouvementFour.setSelected(false);
        });

        mouvementFour.setOnAction(e->{
            this.para.mouvementFour();
            mouvementFour.setSelected(true);
            mouvementEightWithPassingWall.setSelected(false);
            mouvementEightWithoutPassingWall.setSelected(false);
        });

        VBox v = new VBox(OftenUse.SPACE_FOR_BUTTON);
        v.setPrefSize(View.WIDTH, View.HEIGHT-20);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(explcationMouvLabel, mouvementEightWithoutPassingWall, mouvementEightWithPassingWall, mouvementFour);

        Button buttonExit = new Button("Valider et revenir en arrière");
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        HBox exit = new HBox(OftenUse.TEXT_SIZE);
        exit.setAlignment(Pos.CENTER);
        exit.getChildren().add(buttonExit);

        bp.setTop(title);
        bp.setCenter(v);
        bp.setBottom(exit);

        this.s = new Scene(bp);

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });
    }
}
