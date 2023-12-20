package main.java.fr.univlille.view.gameview.allview.parametersview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.View;

public class PlayerParameter extends View{
    private Scene s ;
    private static final String TITLE = "Mode de jeu";

    private Parameters para;
    private static final String IA = "IA";
    private static final String PLAYER = "PLAYER";

    /**
     * Constructeur de la classe {@link MovementParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public PlayerParameter(Parameters para){
        this.para=para;
        this.start();
    }   

    @Override
    public Scene getMyScene() {
        return this.s;
    }

    @Override
    public String getTitle() {
        return PlayerParameter.TITLE;
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

        Label explcationMouvLabel = new Label("Ici vous pouvez choisir le mode de jeu voulu.");

        explcationMouvLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        Label choice1 = new Label("Monstre : ");
        choice1.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        Label choice2 = new Label("Chasseur : ");
        choice2.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        ChoiceBox<String> ch1 = new ChoiceBox<>();
        ch1.getItems().addAll(PLAYER,IA);

        ChoiceBox<String> ch2 = new ChoiceBox<>();
        ch2.getItems().addAll(PLAYER,IA);

        HBox h1 = new HBox(choice1, ch1);
        h1.setAlignment(Pos.CENTER);

        HBox h2 = new HBox(choice2, ch2);
        h2.setAlignment(Pos.CENTER);

        ch1.setOnAction( e -> this.para.setPlayModeMonster(ch1.getSelectionModel().selectedIndexProperty().intValue()));

        ch2.setOnAction( e -> this.para.setPlayModeHunter(ch2.getSelectionModel().selectedIndexProperty().intValue()));

        VBox v = new VBox(explcationMouvLabel, h1, h2);
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

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });

        HBox.setMargin(buttonExit, new Insets(0, 0, 20, 0));

        DropShadow dropShadow = new DropShadow();
        buttonExit.setEffect(dropShadow);
    }
}
