package main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.parametersview.KnowledgeParameter;

public class ChoiceLabyParameter extends VBox{
    
    private Parameters para;


    /**
     * Constructeur de la classe {@link KnowledgeParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public ChoiceLabyParameter(Parameters para){
        this.para = para;
        this.start();
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de connaissance du labyrinthe.<br>
     */
    public void start(){
        ChoiceBox<String> box = new ChoiceBox<>();

        Label explicationLaby = new Label("Ici vous pouvez choisir le labyrinthe que vous voulez utiliser.\n Soit un labyrinthe aléatoire, soit un des trois labyrinthe prédéfinis.");
        Label numLabyPredef = new Label("Labyrinthe prédéfinis :");

        explicationLaby.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        numLabyPredef.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        RadioButton labyRandom = new RadioButton("Labyrinthe aléatoire");
        RadioButton labyPredef = new RadioButton("Labyrinthe prédéfinis");
        box.getItems().addAll("Petit labyrinthe", "Labyrinthe moyen", "Grand labyrinthe");

        labyPredef.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        labyRandom.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        labyRandom.setSelected(true);
        box.setDisable(true);

        labyRandom.setOnAction(e->{
            this.para.labyRandom();
            labyRandom.setSelected(true);
            labyPredef.setSelected(false);

            box.setDisable(true);
        });

        labyPredef.setOnAction(e->{
            labyPredef.setSelected(true);
            labyRandom.setSelected(false);

            box.setDisable(false);
        });

        box.setOnAction( e -> {
            int temp =box.getSelectionModel().selectedIndexProperty().intValue();
            switch (temp) {
                case 0:
                    this.para.setLabyPredef(AllParameters.LABYPREDEF1);
                    break;
                case 1:
                    this.para.setLabyPredef(AllParameters.LABYPREDEF2);
                    break;
                case 2:
                    this.para.setLabyPredef(AllParameters.LABYPREDEF3);
                    break;
                default:
                    break;
            }
        });


        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(numLabyPredef,box);

        this.getChildren().addAll(explicationLaby, labyRandom, labyPredef, h);

        this.setAlignment(Pos.CENTER);

    }
}
