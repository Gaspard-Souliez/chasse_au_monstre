package main.java.fr.univlille.view.gameview.allview.iaview;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.View;

public class IAHunterView extends View{
    private Scene s ;
    private static final String TITLE = "Tour du chasseur IA";  

    public IAHunterView(){
        this.start();
    }
    
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    @Override
    public String getTitle() {
        return IAHunterView.TITLE;
    }

    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);



        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);

        
        Text t = new Text("Le Chasseur à joué, à votre tour.");
        t.setFont(OftenUse.FONT_FOR_WIN);

        Button go = new Button("Jouer");
        go.setFont(OftenUse.FONT_FOR_BUTTON);
        
        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(t,go);

        VBox bottom = new VBox(b);
        bottom.setAlignment(Pos.CENTER);

        bp.setTop(title);
        bp.setCenter(v);
        bp.setBottom(bottom);
        
        BorderPane.setAlignment(title, Pos.CENTER);

        b.setOnAction(e -> notifyObservers());

        go.setOnAction(e -> notifyObservers(AllViewEnum.HUNTERVIEW));
        
        s = new Scene(bp);

    }

}
