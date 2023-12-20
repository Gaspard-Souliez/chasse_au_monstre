package main.java.fr.univlille.view.gameview.allview;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * 
 * Classe {@code MainView} permettant d'afficher en interface graphique les différentes pages.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 * @see Subject
 * @see Observer
 */
public class MainView extends Stage implements Observer{
    /**
     * Attribut {@code screen} permettant de récuperer l'écran sur lequel nous avons lancé l'interface graphique.<br>
     */
    public static final Screen screen = Screen.getPrimary();

    /**
     * Attribut {@code bounds} permettant d'obtenir les limites visuelles de l'écran. Il nous sert plus précisement à savoir la taille.<br>
     */
    public static final Rectangle2D bounds = screen.getVisualBounds();
    public static double screenWidth = bounds.getWidth();
    public static double screenHeight = bounds.getHeight();

    private Map<AllViewEnum,View> map;

    /**
     * Constructeur de la classe {@link MainView} qui permet d'instancier une nouvelle vu, et de la lié avec l'objet {@code menu} passé en paramètre.<br>
     * Il lance également l'affichage du Menu.
     */
    public MainView(){
        Parameters para = new Parameters();
        this.map = new HashMap<>(); 
        this.map.put(AllViewEnum.PLAYVIEW, new PlayView());
        this.map.put(AllViewEnum.MENUVIEW, new MenuView((PlayView)this.map.get(AllViewEnum.PLAYVIEW)));
        this.map.put(AllViewEnum.PARAMETERSVIEW, new ParametersView(para));
        this.map.put(AllViewEnum.RULESVIEW, new RulesView());
        this.map.put(AllViewEnum.HUNTERWIN, new HunterWinView());
        this.map.put(AllViewEnum.MONSTERWIN, new MonsterWinView());
        for (View v : this.map.values()) {
            v.attach(this);
        }
        this.start();
    }

    /**
     * Méthode {@code update} permettant d'afficher le Menu lorsqu'elle est appelée.
     */
    @Override
    public void update(Subject subj) {
        update(subj, AllViewEnum.MENUVIEW);
    }

    /**
     * Méthode {@code update} permettant de changer l'affichage courant par l'affichage passer en paramètre.<br>
     * 
     * @param subj Le sujet qui envoie le changement de donnée.
     * @param data La nouvelle donnée. Ici il s'agit d'un Object de type {@link View} qui représente l'affichage d'une nouvelle fenêtre.
     * 
     * @see Subject
     */
    @Override
    public void update(Subject subj, Object data) {
        View v = this.map.get(data);
        this.setTitle(v.getTitle());
        this.setScene(v.getMyScene());
        this.show();
    }

    /**
     * Méthode {@code start} permettant l'affichage des fenêtres.<br>
     */
    public void start(){
        this.setMaximized(true);
        this.setTitle(this.map.get(AllViewEnum.MENUVIEW).getTitle());
        this.setScene(this.map.get(AllViewEnum.MENUVIEW).getMyScene());
        this.show();
    }
}
