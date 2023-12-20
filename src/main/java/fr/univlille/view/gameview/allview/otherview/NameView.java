package main.java.fr.univlille.view.gameview.allview.otherview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.entity.Hunter;import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.entity.HunterView;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.View;

public class NameView extends View{
    private Scene s ;
    private static final String TITLE = "Prenom";  


    /**
     * Constructeur de la classe {@link HunterView} qui permet de construire l'affichage de la vue du Chasseur.
     * 
     * @param hunter Le Chasseur lié à sa vue.
     * @see Hunter
     */
    public NameView(){
        this.start();
    }
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    @Override
    public String getTitle() {
        return NameView.TITLE;
    }

    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label("Choisissez vos prénoms");
        title.setFont(OftenUse.FONT_FOR_TITLE);

        Label monstre = new Label("Monstre");
        monstre.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        Label hunter = new Label("Chasseur");
        hunter.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        TextField nameMonstre = new TextField();
        TextField nameHunter = new TextField();

        
        
        VBox h1 = new VBox(OftenUse.SPACE_FOR_LABEL_PLAYER);
        h1.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(monstre, nameMonstre);
        
        VBox h2 = new VBox(OftenUse.SPACE_FOR_LABEL_PLAYER);
        h2.setAlignment(Pos.CENTER);
        h2.getChildren().addAll(hunter, nameHunter);

        HBox v = new HBox(OftenUse.SPACE_FOR_NAME_PLAYERS);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h1,h2);

        
        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        
        Button valid = new Button("Valider");
        valid.setFont(OftenUse.FONT_FOR_BUTTON);

        HBox hValid = new HBox();
        hValid.setAlignment(Pos.CENTER);
        hValid.getChildren().add(valid);
        
        VBox all = new VBox(OftenUse.SPACE_FOR_BUTTON);
        all.setAlignment(Pos.CENTER);
        all.getChildren().addAll(v,hValid);
        
        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(b);

        bp.setTop(title);
        bp.setCenter(all);
        bp.setBottom(h);
        BorderPane.setAlignment(title, Pos.CENTER);

        valid.setOnAction(e -> {
            Game.nameHunter = nameHunter.getText();
            Game.nameMonster = nameMonstre.getText();
            notifyObservers(AllViewEnum.NAMEVIEW);
        });
        
        b.setOnAction(e -> notifyObservers());
        
        s = new Scene(bp);

        DropShadow dropShadow = new DropShadow();
        valid.setEffect(dropShadow);
        b.setEffect(dropShadow);

        HBox.setMargin(b, new Insets(0, 0, 40, 0));
    }
}
